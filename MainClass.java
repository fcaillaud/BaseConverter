public class MainClass
{
	public static void main(String[] args)
	{
		//Créer son nombre à convertir avec sa base de départ
		Base startBase = new Base(2);
		Number numToConvert = new Number("1001100", startBase);

		//Créer sa base d'arrivée
		Base endBase = new Base(10);

		//Créer son convertisseur de base
		BaseConverter converter = new BaseConverter(numToConvert);

		//Et convertir le nombre de la base de départ à la base d'arrivée
		System.out.println(numToConvert);
		converter.ConvertTo(endBase);
		System.out.println(numToConvert);
	}
}