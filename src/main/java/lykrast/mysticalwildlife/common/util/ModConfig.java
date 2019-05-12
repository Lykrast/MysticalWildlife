package lykrast.mysticalwildlife.common.util;

public class ModConfig {
	//TODO: put back config annotations once they're here
	public static final Spawning SPAWNING = new Spawning();

	public static boolean brushEnabled = true;
	
	public static int krillForageChance = 2000;
	
	public static int cicapteraLovelyEssenceTimeBase = 12000;
	public static int cicapteraLovelyEssenceTimeExtra = 12000;

	public static boolean potionBreedingEnabled = true;
	public static boolean potionBreedingInstantEnabled = true;
	
	public static class Spawning {

		public Entry vrontausaurus = new Entry(6, 3, 4);
		
		public Entry yagaHog = new Entry(12, 4, 5);
		
		public Entry duskLurkerForest = new Entry(8, 2, 4);
		public Entry duskLurkerSpooky = new Entry(14, 4, 6);
		
		public Entry cicapteraAzure = new Entry(10, 4, 6);
		public Entry cicapteraVerdant = new Entry(10, 4, 6);
		public Entry cicapteraCrimson = new Entry(6, 3, 4);
		public Entry cicapteraSandy = new Entry(10, 4, 6);
		public Entry cicapteraWintry = new Entry(10, 4, 6);
		public Entry cicapteraLovely = new Entry(6, 2, 3);
		
		public Entry plumper = new Entry(12, 3, 5);
		
		public Entry krill = new Entry(12, 1, 3);
		
		public static class Entry {
			public int weight;
			public int minGroup;
			public int maxGroup;
			
			public Entry(int weight, int minGroup, int maxGroup) {
				this.weight = weight;
				this.minGroup = minGroup;
				this.maxGroup = maxGroup;
			}
		}
	}
}
