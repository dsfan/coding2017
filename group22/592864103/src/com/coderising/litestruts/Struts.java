package com.coderising.litestruts;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class Struts {

    public static View runAction(String actionName, Map<String, String> parameters) {

        View view = new View();

        //0. 读取配置文件struts.xml
        try {
            InputStream in = new FileInputStream("src/com/coderising/litestruts/struts.xml");
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(in);
            Element root = doc.getDocumentElement();//root
            NodeList rootList = root.getChildNodes();//nodeList
            Node nodeActionLogin = rootList.item(1);
            Node nodeActionLogout = rootList.item(3);

            //actionLogin
            String nameLogin = nodeActionLogin.getAttributes()//action节点的属性
                    .getNamedItem("name")//属性中名为name的pair✅
                    .getNodeValue();//name的值
            String classLogin = nodeActionLogin.getAttributes()//属性
                    .getNamedItem("class")//名为class的pair
                    .getNodeValue();//class的值
            NodeList resListLogin = nodeActionLogin.getChildNodes();
            //result == success
            Node nodeLoginSuccess = resListLogin.item(1);
            String valueLoginSuccess = nodeLoginSuccess.getAttributes().getNamedItem("name").getNodeValue();
            String urlLoginSuccess = nodeLoginSuccess.getTextContent();
            //result == fail
            Node loginFail = resListLogin.item(3);
            String valueLoginFail = loginFail.getAttributes().getNamedItem("name").getNodeValue();
            String urlLoginFail = loginFail.getTextContent();

            //actionLogout
            String nameLogout = nodeActionLogout.getAttributes().getNamedItem("name").getNodeValue();
            String classLogou = nodeActionLogout.getAttributes().getNamedItem("class").getNodeValue();
            NodeList resListLogout = nodeActionLogout.getChildNodes();
            //result == success
            Node nodeLogoutSuccess = resListLogout.item(1);
            String valueLogoutSuccess = nodeLogoutSuccess.getAttributes().getNamedItem("name").getNodeValue();
            String urlLogoutSuccess = nodeLogoutSuccess.getTextContent();
            //result == error
            Node nodeLogoutError = resListLogout.item(3);
            String valueLogoutError = nodeLogoutError.getAttributes().getNamedItem("name").getNodeValue();
            String urlLogoutError = nodeLogoutError.getTextContent();

           /*
         1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
		据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是
		("name"="test" ,  "password"="1234") ,
		那就应该调用 setName和setPassword方法
		*/
            String className = null;
            if (actionName.equals(nameLogin)) {
                className = classLogin;
            } else if (actionName.equals(nameLogout)) {
                className = classLogou;
            }
            Class clz = Class.forName(className);
            Object obj = clz.newInstance();
            parameters.values();
            String name = parameters.get("name");
            String passWord = parameters.get("password");
            Method setName = clz.getDeclaredMethod("setName",java.lang.String.class);
            Method setPassword = clz.getDeclaredMethod("setPassword",java.lang.String.class);
            setName.invoke(obj, name);
            setPassword.invoke(obj, passWord);

 		/*
        2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
		*/
            Method exectue = clz.getDeclaredMethod("execute");
            String result = exectue.invoke(obj).toString();

 		/*
        3. 通过反射找到对象的所有getter方法（例如 getMessage）,
		通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,
		放到View对象的parameters
		*/
            Method[] methods = clz.getDeclaredMethods();
            Method[] methodsGet = new Method[methods.length];
            int methodIndex = 0;
            for (Method m : methods) {
                if (m.getName().startsWith("get")) {
                    methodsGet[methodIndex] = m;
                    methodIndex++;
                }
            }
            String[] methodNames = new String[methodIndex];
            for (int i = 0; i < methodIndex; i++) {
                methodNames[i] = methodsGet[i].getName();
            }
            String[] attributeNames = new String[methodIndex];
            for (int i = 0; i < methodIndex; i++) {
                attributeNames[i] = methodNames[i].substring(3, 4).toLowerCase() + methodNames[i].substring(4);
            }

            Map<String,String> map = new HashMap<String,String>();
            for (int i = 0; i < methodIndex; i++) {
                map.put(attributeNames[i],methodsGet[i].invoke(obj).toString());
            }
            view.setParameters(map);

 		/*
        4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，
		放到View对象的jsp字段中。
        */
 		if (actionName.equals(nameLogin)){
 		    if (result.equals(valueLoginSuccess)){
 		        view.setJsp(urlLoginSuccess);
            }else if (result.equals(valueLoginFail)){
 		        view.setJsp(urlLoginFail);
            }
        }else if (actionName.equals(nameLogout)){
 		    if (result.equals(valueLogoutSuccess)){
 		        view.setJsp(urlLogoutSuccess);
            }else if (result.equals(valueLogoutError)){
 		        view.setJsp(urlLogoutError);
            }
        }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return view;
    }


}
