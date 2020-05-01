package challenge;

public class CriptografiaCesariana implements Criptografia {

    private StringBuffer alfabeto = new StringBuffer("abcdefghijklmnopqrstuvwxyz"); 
    private Integer numeroCasas = 3;

    @Override
    public String criptografar(String texto) 
    {
        if (texto.isEmpty() )
        {
           throw new IllegalArgumentException();
        } 
            StringBuffer cifrado = new StringBuffer();
            texto = texto.toLowerCase();
            for (int i = 0; i < texto.length(); i++)
            {
                Integer index = alfabeto.indexOf(Character.toString(texto.charAt(i)));
        
                if (index == -1)
                    cifrado.append(Character.toString(texto.charAt(i)));
                else
                {        
                        index = index + numeroCasas;
        
                        if (index > alfabeto.length())
                                index = index - alfabeto.length();
        
                                cifrado.append(alfabeto.charAt(index));
                }        
            }
            return cifrado.toString();
    }

    @Override
    public String descriptografar(String texto) 
    {
        if (texto.isEmpty() )
        {
           throw new IllegalArgumentException();
        } 
           StringBuffer decifrado = new StringBuffer();
            texto = texto.toLowerCase();
            for (int i = 0; i < texto.length(); i++)
            {
                Integer index = alfabeto.indexOf(Character.toString(texto.charAt(i)));
        
                if (index == -1)
                decifrado.append(Character.toString(texto.charAt(i)));
                else
                {        
                        index = index - numeroCasas;
        
                        if (index < 0)
                            index = alfabeto.length() + index;
        
                                decifrado.append(alfabeto.charAt(index));
                }        
            }
            return decifrado.toString();

    }
}
