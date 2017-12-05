import gi

gi.require_version('Gtk', '3.0')
from gi.repository import Gtk, Gdk
import enum


class Direction(enum.Enum):
    N = 0
    W = 1
    S = 2
    E = 3

    def next(self):
        if self == Direction.N:
            return Direction.E
        elif self == Direction.E:
            return Direction.S
        elif self == Direction.S:
            return Direction.W
        elif self == Direction.W:
            return Direction.N


class PathPoint():
    def __init__(self, point, visible):
        self.x = point[0]
        self.y = point[1]
        self.point = (self.x, self.y)
        self.visible = visible


class TurtlePath(Gtk.DrawingArea):
    def __init__(self):
        super(TurtlePath, self).__init__()
        width, height = 500, 500
        self.path_size = 5.
        self.set_size_request(width, height)
        self.connect("draw", self.on_draw)
        starting_point = (width / 2., height / 2.)
        self.turtle_coord = PathPoint(starting_point, False)
        self.path = [self.turtle_coord]

    def on_draw(self, wid, cr):
        cr.set_source_rgba(0, 0, 0, .6)
        cr.set_line_width(self.path_size)

        for i in range(1, len(self.path)):
            c1, c2 = self.path[i - 1], self.path[i]
            cr.move_to(c1.x, c1.y)
            if c2.visible:
                cr.line_to(c2.x, c2.y)
                cr.stroke()

        cr.set_source_rgb(0, 255, 0)
        cr.move_to(self.turtle_coord.x, self.turtle_coord.y)
        cr.arc(self.turtle_coord.x, self.turtle_coord.y, self.path_size, 0, 6)
        cr.fill()

        return True


class MainWindow(Gtk.Window):

    def __init__(self):
        super(MainWindow, self).__init__(title="Turtle go")
        self.entry = Gtk.Entry()

        vbox = Gtk.VBox(spacing=6)
        self.add(vbox)

        self.init_drawing_area(vbox)
        self.init_controls(vbox)
        self.set_resizable(False)

        self.direction = Direction.N

    def init_drawing_area(self, vbox):
        self.turtle = TurtlePath()
        vbox.add(self.turtle)

    def init_controls(self, vbox):
        hbox_options = Gtk.HBox(spacing=6)
        vbox.add(hbox_options)

        self.entry.set_text("FWD 10")
        self.entry.connect("key-release-event", self.on_key_release)
        hbox_options.pack_start(self.entry, True, True, 0)

        self.button_go = Gtk.Button(label="Go")
        self.button_go.connect("clicked", self.on_button_go_clicked)
        hbox_options.pack_start(self.button_go, True, True, 0)

        self.check_visible = Gtk.CheckButton("Visible path")
        self.check_visible.connect("toggled", self.on_visible_toggled)
        self.check_visible.set_active(True)  # by default the path will be visible
        hbox_options.pack_start(self.check_visible, True, True, 0)

    def on_key_release(self, widget, ev, data=None):
        if ev.keyval == Gdk.KEY_Return:
            text = self.entry.get_text()
            self.parse_command(text)

    def on_button_go_clicked(self, widget):
        text = self.entry.get_text()
        self.parse_command(text)

    def on_visible_toggled(self, button):
        value = button.get_active()
        self.visible = value

    def parse_command(self, text):
        split = text.split(' ')
        if len(split) == 0:
            return

        command = split[0]
        prev_point = self.turtle.path[-1].point
        new_point = None
        if command == 'TRN':
            self.direction = self.direction.next()
        elif command == 'FWD' and len(split) > 1:
            distance = float(split[1])
            if self.direction == Direction.N:
                new_point = (prev_point[0], prev_point[1] - distance)
            elif self.direction == Direction.S:
                new_point = (prev_point[0], prev_point[1] + distance)
            elif self.direction == Direction.W:
                new_point = (prev_point[0] - distance, prev_point[1])
            else:  # self.direction == Direction.E
                new_point = (prev_point[0] + distance, prev_point[1])
        elif command == 'BGN':
            new_point = self.turtle.path[0].point

        if new_point:
            self.turtle.turtle_coord = PathPoint(new_point, self.visible)
            self.turtle.path.append(self.turtle.turtle_coord)
            self.turtle.queue_draw()


if __name__ == '__main__':
    win = MainWindow()
    win.connect("delete-event", Gtk.main_quit)
    win.show_all()
    Gtk.main()
