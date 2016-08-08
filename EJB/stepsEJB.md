#Mini-tutorial for EJB-Servlet-JSP workflow  

Follow these steps to create a simple addition Java Enterprise application in NetBeans:

1. Create Enterprise Application Project  
    Open NetBeans IDE and hold Ctrl + Shift + N -> Java EE -> Enterprise Application -> Next. Set the name of your application -> Next -> the defaults are fine -> Finish. 

2. Create your EJB  
    Right click on your Enterprise Application-ejb -> New -> Session Bean -> Choose whatever name suits you (eg. calcbean), define your package (eg. SessionBean) and choose Stateless (u can select other, depending on your needs), check  Local interface (you can select other, depending on your application requirements).

3. Put business logic into your EJB  
    In the right hand side editor, make changes by Right click -> Insert Code -> Add Business Method. In the Dialog that opens up give your business method a name (eg. addition), return type (eg. Integer) and parameter input (eg. two int inputs). Find the example below:

    ```java
    package SessionBean;
    
    import javax.ejb.Stateless;
    
    // Stateless EJB with LocalBean view (default when no interface is implementated)@Statelesspublic class Calculator {
    
        // Business method (public) that may be invoked by EJB clients
        public int addition(int a, int b) {
            return a + b;
        }
    }
    ```
4. Create Servlet which will call your business logic  
    Right click on your Enterprise Application-war -> New -> Servlet -> define servlet name (eg. calcservlet) and its package (eg. SessionBean)  -> Next -> check "Add information to deployment descriptor (web.xml)" -> Finish.

5. Define dependency between your Servlet and EJB.  
    Your controller (Servlet) need to use your EJB. You don't want to do any lookups or nasty boilerplate code. You just defines that your Servlet will use your EJB by using annotation. to do this in the editor space,  Right click -> Insert Code ->  Call Enterprise Bean, expand Enterprise Application-ejb and select your bean.

    ```java
    @EJB
    private calcbeanLocal calcbean;
    You put this as a field in your servlet class, so it should be something like this:
    
    public class calcservlet extends HttpServlet {
    
        @EJB
        private calcbeanLocal calcbean;
    
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    // ...
    ```
6. Put controller logic into your Servlet  
    NetBeans by defaults delegates all HTTP Method requests into one method - processRequest(-), so it's the only method you should modify.
    Find the example below:

    ```java
    // ...
    out.println("<body>");
    int a = Integer.parseInt(request.getParameter("t1"));
    int b = Integer.parseInt(request.getParameter("t2"));
    out.println("<h1>SUM = "+calcbean.addition(a,b)+"</h1>")
    out.println("</body>");
    // ...
    ```
7. Create JSP file  
    Right click on your Enterprise Application-war -> New -> JSP ->  type the file name (eg. calcjsp). Leave the folder input value empty. -> Finish.

8. Fill JSP file with user interface code  
    It should be simple and push the request to your servlet URL mapping. In my case its something like the following:

    ```jsp
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
        </head>
        <body>
            <form action="calcservlet" method="POST">
                <input type="text" name="t1" />
                <input type="text" name="t2" />
                <input type="submit" value="ADD" />
            </form>
        </body>
    </html>
    ```
    Just watch the form action attribute value (it should be your Servlet URL mapping).

9. Deploy and Run your application  
    - Right click on your Enterprise Application then click Deploy, wait.  
    - Right click on your Enterprise Application then click Run.  
    - The application will open in your default browser. Next thing is to modify the Web Application URL to add the JSP file name.  
    Combine it all together and you should have something like:  
    [http://localhost:8080/EnterpriseApplication-war/calcjsp.jsp](http://localhost:8080/EnterpriseApplication-war/calcjsp.jsp)

    In two input feilds you type the values and after clicking 'ADD' you should see the addition result.

Optional Steps:
  - You may modify your "Welcome page" in web.xml under pages tab of Enterprise Application-war > Configuration project directory.
  - Another way to launch your application is through the GlassFish Servers web Control Panel which is located at [http://localhost:4848/](http://localhost:4848/).


Adapted from [Piotr Nowicki's  answer to Compile/Run EJB Code in NetBeans (Beginner)](http://stackoverflow.com/a/8659189/2534513) at Stackoverflow. See step-by-step video at: http://youtu.be/490uvFPbnA0.