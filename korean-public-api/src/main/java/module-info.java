module com.kyj.api.korean.pub {
	requires java.base;
	requires com.fasterxml.jackson.annotation;
	requires com.fasterxml.jackson.core;
	requires com.fasterxml.jackson.databind;
	requires org.slf4j;
	requires org.apache.commons.io;
	requires velocity;
	
	opens com.kyj.api.korean.pub;
	opens com.kyj.api.korean.pub.models.newaddress;
	opens com.kyj.api.korean.pub.models.arvInformation;
	
	exports com.kyj.api.korean.pub;
	exports com.kyj.api.korean.pub.models.newaddress;
	exports com.kyj.api.korean.pub.models.arvInformation;
}