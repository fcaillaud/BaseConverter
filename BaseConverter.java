public class BaseConverter
{
	private Number m_Number;

	public BaseConverter(Number pNumber)
	{
		m_Number = pNumber;
	}

	public void ConvertTo(Base pBase)
	{
		try
		{
			ConvertToDecimalBase();
			ConvertFromDecimalTo(pBase);
		}
		catch(WrongSymbolException|WrongBaseException e)	
		{
			System.err.println(e.getClass().getName() + " : " + e.getMessage());
		}
	}

	private void ConvertToDecimalBase()
	{
		int tab[] = m_Number.GetSplitDigits();

		long intValue = 0;
		for(int i = 0; i < tab.length; ++i)
			intValue += tab[Math.abs(i - (tab.length - 1))] * Math.pow(m_Number.GetBase().GetIntBase(), i);

		m_Number.Set(String.valueOf(intValue), new Base(10));
	}

	private void ConvertFromDecimalTo(Base pBase) throws WrongBaseException, WrongSymbolException
	{
		if(m_Number.GetBase().GetIntBase() != 10)
			throw new WrongBaseException("Wrong base : need a decimal base here.");

		int tab[] = m_Number.GetSplitDigits();

		long intValue = 0;
		for(int i = 0; i < tab.length; ++i)
			intValue += tab[Math.abs(i - (tab.length - 1))] * Math.pow(10, i);


		long euclideRes[];
		long r;
		String newStrValue = "", temp = "";
		char c[] = new char[1];

		while(intValue > 0)
		{
			euclideRes = EuclideDivision(intValue, pBase.GetIntBase());
			intValue = euclideRes[0];
			r = euclideRes[1];

			if(r < 10) {
				temp += String.valueOf(r);
			} else if(r < 36) {
				c[0] = (char)('A' + (r - 10));
				temp += new String(c); 
			} else {
				throw new WrongSymbolException("Wrong symbol : the symbol is out of alphabet.");
			}
		}

		char newCharValue[] = new char[temp.length()];
		for(int i = 0; i < temp.length(); ++i)
			newCharValue[i] = temp.charAt(Math.abs(i - (temp.length() - 1)));

		newStrValue = new String(newCharValue);

		m_Number.Set(newStrValue, pBase);
	}

	public long[] EuclideDivision(long a, long b) {
		long r = a, q = 0, n = 0, aux = b;

		while (aux <= a) 
		{
			aux = (aux << 1);
			n++;
		}

		while (n > 0) 
		{
			aux = (aux >> 1);
			n--;
			q = (q << 1);
			if (r >= aux) 
			{
				r = r - aux;
				q++;
			}
		}

		long[] res = { q, r };
		return res;
	}
}