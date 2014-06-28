package controllers.actions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import java.util.Map.Entry;

import play.Logger;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.With;
import controllers.actions.VerboseAction.Verbose;

// Logging class for HTTP actions + parameters
public class VerboseAction  extends Action<Verbose> {
	
	@With(VerboseAction.class)
	@Target({ElementType.TYPE, ElementType.METHOD})
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Verbose {
	   boolean value() default true;
	}

	public Result call(Http.Context ctx) throws Throwable {
		if(configuration.value()) {
			String queryString = "";
			Map<String, String[]> keyval = ctx.request().body().asFormUrlEncoded();
			if(keyval!=null)
			{
				for(Entry<String,String[]> entry :  keyval.entrySet())
				{
					queryString += entry.getKey()+" -> " + entry.getValue()[0] + "\n";
				}
			}
		      Logger.info("Calling action for " + ctx.request().path() +"\n"+ queryString);  
		      
		    }
		    return delegate.call(ctx);
	  }

}
