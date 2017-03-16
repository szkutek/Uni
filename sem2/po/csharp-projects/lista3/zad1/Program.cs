using System;
using ListaLib;

public class Program {
	public static void Main() {

		Lista<int> L1 = new Lista<int>();
		L1.PushFront(2);
		L1.PushFront(1);
		L1.PushBack(3);
		L1.PushBack(4);
		L1.PushBack(5);

		L1.Wypisz();

		int val = L1.PopFront();
		val = L1.PopFront();

		Console.WriteLine("PopFront: {0}\n",val);

		val = L1.PopBack();

		Console.WriteLine("PopBack: {0}\n",val);

		L1.Wypisz();

	}
}