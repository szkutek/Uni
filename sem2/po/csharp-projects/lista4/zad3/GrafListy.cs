namespace GrafLLib {

	public class GrafL<T> {

		;

		private class Elem {
			
			protected Elem next;
			protected Elem prev;
			protected T val;

			protected Elem(){ }
			protected Elem(Elem prev, Elem next, T val) {
					this.prev = prev;
					this.next = next;
					this.val = val;
			}
		}

	}
}