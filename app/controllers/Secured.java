package controllers;

import play.Logger;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;


// AOP Interceptor for security when not logged in
public class Secured extends Security.Authenticator {
    
    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("userID");
    }
    
    @Override
    public Result onUnauthorized(Context ctx) {
    	Logger.info("Access denied");
    	ctx.flash().put("message", "Not logged in");
        return redirect(routes.Application.landing());
    }
}