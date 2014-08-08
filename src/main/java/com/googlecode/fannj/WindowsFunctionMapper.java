package com.googlecode.fannj;

import com.sun.jna.FunctionMapper;
import com.sun.jna.NativeLibrary;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class WindowsFunctionMapper implements FunctionMapper{

    private static final Logger logger = Logger.getLogger(WindowsFunctionMapper.class.getName());
    private final Map<String,String> translations;
    
    public WindowsFunctionMapper(){
        translations = new HashMap<String,String>();
        loadTranslations();
    }
    
    private void loadTranslations(){
        
        try{
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            this.getClass().getResourceAsStream("WindowsFunctionNames.txt")));
            String s = in.readLine();
            while (s != null){
                addTranslation(s);
                s = in.readLine();
            }
            in.close();
        }
        catch(IOException ex){
            throw new IllegalStateException("Unable to load windows function names", ex);
        }
    }
    
    private void addTranslation(String windowsName){
        
        if (windowsName != null && !windowsName.isEmpty() && !windowsName.startsWith("#")){
            String cleanName = windowsName;
            if (windowsName.startsWith("_"))
                cleanName = windowsName.substring(1);
            int pos = cleanName.indexOf("@");
            if (pos > 0)
                cleanName = cleanName.substring(0, pos);
            logger.log(Level.FINE, "{0} = {1}", new Object[]{cleanName.trim(), windowsName.trim()});
            translations.put(cleanName.trim(), windowsName.trim());
        }
    }       
    
    @Override
    public String getFunctionName(NativeLibrary nl, Method method) {
        String result = translations.get(method.getName());
        return result == null ? method.getName() : result;
    }
    
}
