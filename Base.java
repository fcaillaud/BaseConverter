public class Base
{
	private int m_intBase;

	public Base()
	{
		m_intBase = 10;
	}

	public Base(int pIntBase)
	{
		m_intBase = pIntBase;
	}

	public int GetIntBase()
	{
		return m_intBase;
	}

	public void SetIntBase(int pIntBase)
	{
		m_intBase = pIntBase;
	}

	public String GetStrBase()
	{
		String res = "";
		switch(m_intBase)
		{
			case 2:
				res = "binary base";
				break;
			case 8:
				res = "octal base";
				break;
			case 10:
				res = "decimal base";
				break;
			case 16:
				res = "hexadecimal base";
				break;
			default:
				res = "base " + m_intBase;
				break;
		}
		return res;
	}

	public String toString()
	{
		return "Base Object " 	+ Integer.toHexString(System.identityHashCode(this)) 
								+ " : " 
								+ GetStrBase();
	}
}