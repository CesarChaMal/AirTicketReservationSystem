package com.crossover.techtrial.conf;

import org.jasypt.encryption.pbe.PBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.spring.properties.EncryptablePropertyPlaceholderConfigurer;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.crossover.techtrial.util.EncryptionDecryption;

@Configuration
//@PropertySource({ "classpath:app.properties" })
//@PropertySource(name = "appProperties", value = {"classpath:a.properties", "classpath:b.properties"})
@PropertySource(name = "appProperties", value = {"classpath:app.properties"})
public class PropertyConfig {
    
	@Bean
	public static PropertyPlaceholderConfigurer propertyConfigurer() {
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();		
		Resource[] resources = new ClassPathResource[] { new ClassPathResource( "app.properties" ) };
		ppc.setLocations( resources );
		ppc.setIgnoreUnresolvablePlaceholders( true );
		return ppc;
	}
	
    @Bean
    public EncryptablePropertyPlaceholderConfigurer propertyConfigurer2() {
        EncryptablePropertyPlaceholderConfigurer configurer = new EncryptablePropertyPlaceholderConfigurer(encryptor());
        
        // set the SystemPropertiesMode to override
        configurer.setSystemPropertiesMode(PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE);
        
        // set the locations of our property files
        Resource[] locations = new ClassPathResource[2];
        locations[0] = new ClassPathResource("app.properties");
        locations[1] = new ClassPathResource("log4j.properties");

        configurer.setLocations(locations);
        
        return configurer;
    }

    @Bean
    public PBEStringEncryptor encryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        
        // Set the password to what was in the EncryptionDecryption class
        encryptor.setPassword("Rkrl/ZTTdRxtiincyC8veSrQLKikrX8I");
        EncryptionDecryption.getInstance().setEncryptor(encryptor);
      
        return encryptor;
    }
    
}
