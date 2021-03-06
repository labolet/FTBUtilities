package latmod.ftbu.api.guide;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface GuideInfo
{
	public String key() default "";
	public String info();
	public String def() default "";
}