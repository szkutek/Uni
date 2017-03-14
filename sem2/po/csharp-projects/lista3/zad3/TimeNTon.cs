namespace TimeNTonLib{

	sealed class TimeNTon{

		static int pos = 0;
		static int N = 2;
		static TimeNTon[] instance;
		static TimeNTon instance2;


		TimeNTon(){ }
		
		public static TimeNTon Instance(double t){
			if (instance == null)
				instance = new TimeNTon[N];

			if (t<10.0 && t>12.0) {
				if (instance2 == null) instance2 = new TimeNTon();
				System.Console.WriteLine("pojedyncza instacja\n");
				return instance2;
			}
			else {
				if (instance[pos] == null){
					instance[pos] = new TimeNTon();
					System.Console.WriteLine("tworze obiekt nr {0}\n",pos);
				}
				TimeNTon tmp = instance[pos];
				pos = (pos+1)%(N-1);
				System.Console.WriteLine("zwracam obiekt nr {0}\n",pos);
				return tmp;
			}

		}
	}
}