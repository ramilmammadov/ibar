package az.ibar.IbarLoanOrder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration

public class SwaggerConfig {

    private static final String TITLE = "IBAR API";


    private static final String VERSION = "1.0.0";


    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .description("IBAR api")
                .contact(new Contact("Ramil Mammadov", "https://ibar.az/", "ramilmammadof@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version(VERSION)
                .build();
    }


    @Bean
    public Docket productPublicApi1() {
        return new Docket(DocumentationType.SWAGGER_2) //
                .groupName("Public") //
                .globalOperationParameters(globalParametersForAll()) //

                .select() //
                .apis(RequestHandlerSelectors.basePackage("az.ibar.IbarLoanOrder.controller")) //
                .paths(PathSelectors.regex("/public.*")) //
                .build() //
                .apiInfo(metadata());
    }


    @Bean
    public Docket productPrivateApi2() {
        List<Parameter> list = globalParametersForRegistered();
        list.addAll(globalParametersForAll());

        return new Docket(DocumentationType.SWAGGER_2) //
                .groupName("Private") //
                .globalOperationParameters(list) //
                .select() //
                .apis(RequestHandlerSelectors.basePackage("az.ibar.IbarLoanOrder.controller")) //
                .paths(PathSelectors.regex("/private.*")) //
                .build() //
                .apiInfo(metadata());
    }


    private List<Parameter> globalParametersForAll() {
        List<Parameter> parameter = new ArrayList<Parameter>();

        parameter.add(new ParameterBuilder()//
                .name("Accept-Language")//
                .description("Language header") //
                .modelRef(new ModelRef("string")) //
                .parameterType("header")//
                .required(false)//
                .build());

        return parameter;
    }

    private List<Parameter> globalParametersForRegistered() {
        List<Parameter> parameter = new ArrayList<Parameter>();

        parameter.add(new ParameterBuilder()//
                .name("Authorization")//
                .description("Authorization header") //
                .modelRef(new ModelRef("string")) //
                .parameterType("header")//
                .required(true)//
                .build());

        return parameter;
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder() //
                .title("IBAR  documentation")//
                .description("IBAR documentation")//
                .version("1.0.0")//
                .build();
    }

}