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

        self.last_seen = datetime.datetime.now(datetime.timezone.utc)

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
            self.name_entry.set_text(contact.name)
            self.surname_entry.set_text(contact.surname)
            self.email_entry.set_text(contact.email)
            self.phone_entry.set_text(contact.phone_number)

        self.grid.attach(name_label, 0, 0, 1, 1)
        self.grid.attach_next_to(self.name_entry, name_label, Gtk.PositionType.RIGHT, 1, 1)
        self.grid.attach_next_to(surname_label, name_label, Gtk.PositionType.BOTTOM, 1, 1)
        self.grid.attach_next_to(self.surname_entry, surname_label, Gtk.PositionType.RIGHT, 1, 1)
        self.grid.attach_next_to(email_label, surname_label, Gtk.PositionType.BOTTOM, 1, 1)
        self.grid.attach_next_to(self.email_entry, email_label, Gtk.PositionType.RIGHT, 1, 1)
        self.grid.attach_next_to(phone_label, email_label, Gtk.PositionType.BOTTOM, 1, 1)
        self.grid.attach_next_to(self.phone_entry, phone_label, Gtk.PositionType.RIGHT, 1, 1)

        if see:
            self.name_entry.set_editable(False)
            self.surname_entry.set_editable(False)
            self.email_entry.set_editable(False)
            self.phone_entry.set_editable(False)
            # last_seen_label = Gtk.Label(label='Last Seen: ' + str(contact.last_seen))
            # self.grid.attach_next_to(last_seen_label, phone_label, Gtk.PositionType.BOTTOM, 1, 1)

        box = self.get_content_area()
        box.add(label)
        box.add(self.grid)

        self.show_all()


class MainWindow(Gtk.Window):

    def __init__(self, db):
        super(MainWindow, self).__init__(title="My contacts")
        self.set_size_request(200, 200)

        self.db = db

        hbox = Gtk.HBox(spacing=6)
        self.add(hbox)

        self.init_controls(hbox)
        self.init_table_of_contacts(hbox)

    def init_table_of_contacts(self, hbox):
        self.table_of_contacts = Gtk.Table()
        hbox.add(self.table_of_contacts)

    def init_controls(self, hbox):
        vbox_options = Gtk.VBox(spacing=6)
        hbox.add(vbox_options)

        self.addButton = Gtk.Button('Add contact')
        self.addButton.connect("clicked", self.addContact)
        vbox_options.pack_start(self.addButton, True, True, 0)

        self.delButton = Gtk.Button('Delete contact')
        self.delButton.connect("clicked", self.delContact)
        vbox_options.pack_start(self.delButton, True, True, 0)

        self.editButton = Gtk.Button('Edit contact')
        self.editButton.connect("clicked", self.editContact)
        vbox_options.pack_start(self.editButton, True, True, 0)

        # self.entry = Gtk.Entry()
        # self.entry.set_text("FWD 10")
        # hbox_options.pack_start(self.entry, True, True, 0)
        #
        # self.button_go = Gtk.Button(label="Go")
        # self.button_go.connect("clicked", self.on_button_go_clicked)
        # hbox_options.pack_start(self.button_go, True, True, 0)
        #
        # self.check_visible = Gtk.CheckButton("Visible path")
        # self.check_visible.connect("toggled", self.on_visible_toggled)
        # self.check_visible.set_active(True)  # by default the path will be visible
        # hbox_options.pack_start(self.check_visible, True, True, 0)

    def addContact(self, widget):
        print('Add')
        cursor = self.db.cursor()

        dialog = ContactDialog(self)
        response = dialog.run()

        if response == Gtk.ResponseType.OK:
            # command = '({}, {}, {}, {})'.format(dialog.name_entry, dialog.surname_entry,
            #                                     dialog.phone_entry, dialog.email_entry)
            try:
                cursor.execute(
                    'insert into Contacts(NAME, SURNAME, PHONE_NUMBER, EMAIL, LAST_SEEN) values (?,?,?,?,?)',
                    (dialog.name_entry.get_text(), dialog.surname_entry.get_text(),
                     dialog.phone_entry.get_text(), dialog.email_entry.get_text(),
                     dialog.last_seen))
                self.db.commit()
                print("New contact was added.")
            except:
                pass

        elif response == Gtk.ResponseType.CANCEL:
            print("The Cancel button was clicked")

        dialog.destroy()

    def delContact(self, widget):
        print('Del')

    def editContact(self, widget):
        print('Edit')


if __name__ == '__main__':
    db = sqlite3.connect('contacts.db')

    win = MainWindow(db)
    win.connect("delete-event", Gtk.main_quit)
    win.show_all()
    Gtk.main()
