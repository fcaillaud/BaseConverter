public class Number
{
	private String m_strValue;

	private Base m_base;

	private int[] m_splitDigits;

	public Number()
	{
		m_strValue = "0";
		m_base = new Base();

		IntegrityTest();
	}

	public Number(String pStrValue)
	{
		m_strValue = pStrValue;
		m_base = new Base();

		IntegrityTest();
	}

	public Number(String pStrValue, Base pBase)
	{
		m_strValue = pStrValue;
		m_base = pBase;

		IntegrityTest();
	}

	public Number(String pStrValue, int pBase)
	{
		m_strValue = pStrValue;
		m_base = new Base(pBase);

		IntegrityTest();
	}

	public String GetStrValue()
	{
		return m_strValue;
	}

	public Base GetBase()
	{
		return m_base;
	}

	public int[] GetSplitDigits()
	{
		return m_splitDigits;
	}

	public void Set(String pStrValue, Base pBase)
	{
		m_strValue = pStrValue;
		m_base = pBase;

		IntegrityTest();
	}

	private int[] SplitDigitsInIntTab() throws WrongSymbolException
	{
		char c;
		int n;
		int res[] = new int[m_strValue.length()];

		for(int i = 0; i < m_strValue.length(); ++i) 
		{
			c = m_strValue.charAt(i);
        	if(c >= '0' && c <= '9') 
            	n = c - '0';	
            else if(c >= 'a' && c <= 'z')
            	n = 10 + (c - 'a');
            else if(c >= 'A' && c <= 'Z')
            	n = 10 + (c - 'A');
            else
            	throw new WrongSymbolException("Wrong symbol, do not correspond to alphanumeric symbol.");

            res[i] = n;
    	}

    	return res;
	}

	public void IntegrityTest()
	{
		try
		{
			m_splitDigits = SplitDigitsInIntTab();

			for(int i = 0; i < m_splitDigits.length; ++i)
				if(m_splitDigits[i] >= m_base.GetIntBase())
					throw new WrongBaseException("Wrong base, one symbol is greater than the base.");
		} 
		catch(WrongSymbolException|WrongBaseException e)	
		{
			System.err.println(e.getClass().getName() + " : " + e.getMessage());
		}
	}

	public String toString()
	{
		return "Number Object " + Integer.toHexString(System.identityHashCode(this)) 
								+ " : " 
								+ m_strValue
								+ " in "
								+ m_base.GetStrBase();
	}
}