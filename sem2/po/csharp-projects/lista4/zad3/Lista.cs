namespace ListaLib {

	public class Lista<T> {

		Elem first;
		Elem last;


		private class Elem {
			
			public Elem next;
			public Elem prev;
			public T val;

			public Elem(){ }
			public Elem(Elem prev, Elem next, T val) {
					this.prev = prev;
					this.next = next;
					this.val = val;
			}
		}

		public Lista(){ }

		public Lista(T val){
			PushFirst(val);
		}

		private void PushFirst(T val){
			Elem e = new Elem(null, null, val);
			this.first = e;
			this.last = e;
		}

		public int IsEmpty(){
			if (this.first == null){
				return 1;
			}
			return 0;
		}

		public void PushFront(T val){
			if (this.first == null){
				PushFirst(val);
				return;
			}
			Elem e = new Elem(null, this.first, val);
			//??
			this.first.prev = e;
			this.first = e;
		}

		public void PushBack(T val){
			if (this.first == null){
				PushFirst(val);
				return;
			}
			Elem e = new Elem(this.last, null, val);
			//??
			this.last.next = e;
			this.last = e;
		}

		public T PopFront(){
			if (this.first == null) 
				throw new System.InvalidOperationException("Lista jest pusta.");

			T v = this.first.val;
			this.first = this.first.next;
			this.first.prev = null;

			return v;
		}

		public T PopBack(){
			if (this.first == null) 
				throw new System.InvalidOperationException("Lista jest pusta.");

			T v = this.last.val;
			this.last = this.last.prev;
			this.last.next = null;

			return v;
		}

		public void Wypisz(){
			Elem tmp = this.first;
			while (tmp != null){
				System.Console.WriteLine("{0} ",tmp.val);
				tmp = tmp.next;
			}
			System.Console.WriteLine("\n");
		}

		public void Remove(T v){
			Elem k = this.first;

			while (k.val != v && k != this.last)
				k = k.next;
				
			k.prev.next = k.next;
			k.next.prev = k.prev;

		}


	}
}