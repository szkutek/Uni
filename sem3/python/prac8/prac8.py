import gi

gi.require_version('Gtk', '3.0')
from gi.repository import Gtk

import sqlite3
import datetime


class ContactDialog(Gtk.Dialog):
    def __init__(self, parent, contact=None, see=False):
        Gtk.Dialog.__init__(self, 'Add contact', parent, 0,
                            (Gtk.STOCK_CANCEL, Gtk.ResponseType.CANCEL,
                             Gtk.STOCK_OK, Gtk.ResponseType.OK))
        self.grid = Gtk.Grid()

        self.last_seen = str(datetime.datetime.now())  # datetime.datetime.utcnow()

        label = Gtk.Label("Create new contact")
        name_label = Gtk.Label(label='Name')
        self.name_entry = Gtk.Entry()
        surname_label = Gtk.Label(label='Surname')
        self.surname_entry = Gtk.Entry()
        email_label = Gtk.Label(label='Email')
        self.email_entry = Gtk.Entry()
        phone_label = Gtk.Label(label='Phone')
        self.phone_entry = Gtk.Entry()

        if contact is not None:
            self.name_entry.set_text(contact[0])
            self.surname_entry.set_text(contact[1])
            self.phone_entry.set_text(contact[2])
            self.email_entry.set_text(contact[3])

            self.phone_entry.set_editable(False)

        self.grid.attach(name_label, 0, 0, 1, 1)
        self.grid.attach_next_to(self.name_entry, name_label, Gtk.PositionType.RIGHT, 1, 1)
        self.grid.attach_next_to(surname_label, name_label, Gtk.PositionType.BOTTOM, 1, 1)
        self.grid.attach_next_to(self.surname_entry, surname_label, Gtk.PositionType.RIGHT, 1, 1)
        self.grid.attach_next_to(phone_label, surname_label, Gtk.PositionType.BOTTOM, 1, 1)
        self.grid.attach_next_to(self.phone_entry, phone_label, Gtk.PositionType.RIGHT, 1, 1)
        self.grid.attach_next_to(email_label, phone_label, Gtk.PositionType.BOTTOM, 1, 1)
        self.grid.attach_next_to(self.email_entry, email_label, Gtk.PositionType.RIGHT, 1, 1)

        if see:
            self.name_entry.set_editable(False)
            self.surname_entry.set_editable(False)
            self.email_entry.set_editable(False)
            self.phone_entry.set_editable(False)
            last_seen_label = Gtk.Label('Last seen: ')
            last_seen = Gtk.Label(str(contact[4]))
            self.grid.attach_next_to(last_seen_label, email_label, Gtk.PositionType.BOTTOM, 1, 1)
            self.grid.attach_next_to(last_seen, last_seen_label, Gtk.PositionType.RIGHT, 1, 1)

        box = self.get_content_area()
        box.add(label)
        box.add(self.grid)

        self.show_all()


class MainWindow(Gtk.Window):

    def __init__(self, db):
        super(MainWindow, self).__init__(title="My contacts")
        self.set_size_request(200, 200)

        self.db = db

        self.init_controls()
        self.init_table_of_contacts()

        self.show_all()

    def init_controls(self):
        self.vbox_options = Gtk.VBox(spacing=4)
        self.vbox_options.set_border_width(10)

        self.filterEntry = Gtk.Entry()
        self.filterEntry.connect('changed', self.filterContacts, self.filterEntry)
        self.vbox_options.pack_start(self.filterEntry, True, True, 0)

        self.addButton = Gtk.Button('Add contact')
        self.addButton.connect("clicked", self.addContact)
        self.vbox_options.pack_start(self.addButton, True, True, 0)

        self.seeButton = Gtk.Button('See contact')
        self.seeButton.connect("clicked", self.seeContact)
        self.vbox_options.pack_start(self.seeButton, True, True, 0)

        self.delButton = Gtk.Button('Delete contact')
        self.delButton.connect("clicked", self.delContact)
        self.vbox_options.pack_start(self.delButton, True, True, 0)

        self.editButton = Gtk.Button('Edit contact')
        self.editButton.connect("clicked", self.editContact)
        self.vbox_options.pack_start(self.editButton, True, True, 0)

    def init_table_of_contacts(self):
        # Setting up the self.grid in which the elements are to be positionned
        self.grid = Gtk.Grid()
        self.grid.set_column_homogeneous(True)
        self.grid.set_row_homogeneous(True)
        self.add(self.grid)

        # Creating the ListStore model
        self.liststore = Gtk.ListStore(str, str, str, str, str)
        self.setup_liststore()
        self.current_filter = None

        # Creating the filter, feeding it with the liststore model
        self.filter = self.liststore.filter_new()
        self.filter.set_visible_func(self.filter_func)

        # Init tree view
        self.treeview = Gtk.TreeView.new_with_model(self.filter)
        for i, column_title in enumerate(["Name", "Surname", "Phone Number", "Email", "Last Seen"]):
            renderer = Gtk.CellRendererText()
            column = Gtk.TreeViewColumn(column_title, renderer, text=i)
            self.treeview.append_column(column)

        self.selected_contact = None
        self.contact = None
        select = self.treeview.get_selection()
        select.connect("changed", self.on_tree_selection_changed)

        # Set up layout
        self.grid.attach(self.vbox_options, 0, 0, 1, 1)
        self.scrollable_treelist = Gtk.ScrolledWindow()
        self.scrollable_treelist.set_vexpand(True)
        self.grid.attach_next_to(self.scrollable_treelist, self.vbox_options, Gtk.PositionType.RIGHT, 5, 3)
        self.scrollable_treelist.add(self.treeview)

    def filterContacts(self, widget, entry):
        text = entry.get_text()
        self.current_filter = text if text != '' else None
        self.filter.refilter()

    def addContact(self, widget):
        cursor = self.db.cursor()

        dialog = ContactDialog(self)
        response = dialog.run()

        if response == Gtk.ResponseType.OK:
            try:
                cursor.execute(
                    'insert into Contacts(NAME, SURNAME, PHONE_NUMBER, EMAIL, LAST_SEEN) values (?,?,?,?,?)',
                    (dialog.name_entry.get_text(), dialog.surname_entry.get_text(),
                     dialog.phone_entry.get_text(), dialog.email_entry.get_text(),
                     dialog.last_seen))
                self.db.commit()
                print("New contact was added.")
                self.setup_liststore()
            except:
                pass
        elif response == Gtk.ResponseType.CANCEL:
            print("The Cancel button was clicked")

        dialog.destroy()

    def seeContact(self, widget):
        if self.selected_contact is not None:
            cursor = self.db.cursor()
            cursor.execute(
                'select * from Contacts where PHONE_NUMBER = ?',
                (self.selected_contact,))
            self.contact = cursor.fetchone()
            dialog = ContactDialog(self, self.contact, see=True)
            dialog.run()

            cursor.execute(
                'update Contacts set LAST_SEEN=? where PHONE_NUMBER = ?',
                (dialog.last_seen, dialog.phone_entry.get_text()))
            self.db.commit()
            print("Last seen was updated.")
            self.setup_liststore()

            dialog.destroy()

    def delContact(self, widget):
        if self.selected_contact is not None:
            cursor = self.db.cursor()
            cursor.execute(
                'delete from Contacts where PHONE_NUMBER = ?',
                (self.selected_contact,))
            self.contact = cursor.fetchone()
            print(self.contact)
            db.commit()
            print("Contact was deleted.")
            self.setup_liststore()

    def editContact(self, widget):
        if self.selected_contact is not None:
            cursor = self.db.cursor()
            cursor.execute(
                'select * from Contacts where PHONE_NUMBER = ?',
                (self.selected_contact,))
            self.contact = cursor.fetchone()
            print(self.contact)
            db.commit()
            dialog = ContactDialog(self, self.contact, see=False)
            response = dialog.run()

            if response == Gtk.ResponseType.OK:
                try:
                    cursor.execute(
                        'update Contacts set NAME = ?, SURNAME=?, PHONE_NUMBER=?, EMAIL=?, LAST_SEEN=? where PHONE_NUMBER = ?',
                        (dialog.name_entry.get_text(), dialog.surname_entry.get_text(),
                         dialog.phone_entry.get_text(), dialog.email_entry.get_text(),
                         dialog.last_seen, dialog.phone_entry.get_text()))
                    self.db.commit()
                    print("Contact was updated.")
                    self.setup_liststore()
                except:
                    pass
            elif response == Gtk.ResponseType.CANCEL:
                print("The Cancel button was clicked")

            dialog.destroy()

    def setup_liststore(self):
        self.liststore.clear()
        cursor = db.cursor()
        cursor.execute('SELECT * FROM Contacts')

        row = cursor.fetchone()
        while row:
            self.liststore.append(list(row))
            row = cursor.fetchone()
        self.current_filter = None

    def filter_func(self, model, iter, data):
        if self.current_filter is None or self.current_filter == "None":
            return True
        else:
            return self.current_filter in model[iter][1]

    def on_tree_selection_changed(self, selection):
        model, treeiter = selection.get_selected()
        if treeiter is not None:
            self.selected_contact = model[treeiter][2]
            print(self.selected_contact)


if __name__ == '__main__':
    db = sqlite3.connect('contacts.db')

    win = MainWindow(db)
    win.connect("delete-event", Gtk.main_quit)
    win.show_all()
    Gtk.main()

    db.close()
