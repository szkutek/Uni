namespace TimeNTonLib{

	sealed public class TimeNTon{

		static int pos = 0;
		static int N = 3;
		static TimeNTon[] instance;
		static TimeNTon instance2;


		TimeNTon(){ }
		
		public static TimeNTon Instance(double t){
		
			if (t<10.0 || t>12.0) {
				if (instance2 == null) {
					instance2 = new TimeNTon();
					System.Console.WriteLine("tworzy pojedyncza instacje\n");

				}
				System.Console.WriteLine("zwraca pojedyncza instacje\n");
				return instance2;
			}
			else {
				if (instance == null)
					instance = new TimeNTon[N];

				if (instance[pos] == null){
					instance[pos] = new TimeNTon();
					System.Console.WriteLine("tworze obiekt nr {0}\n",pos);
				}
				TimeNTon tmp = instance[pos];
				System.Console.WriteLine("zwracam obiekt nr {0}\n",pos);
				pos = (pos+1)%N;
				return tmp;
			}

		}
	}
}