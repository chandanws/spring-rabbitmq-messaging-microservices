package de.jonashackt.transformation;


import de.codecentric.namespace.weatherservice.datatypes.ProductName;
import de.codecentric.namespace.weatherservice.general.ForecastCustomer;
import de.codecentric.namespace.weatherservice.general.ForecastRequest;
import de.jonashackt.model.MethodOfPayment;
import de.jonashackt.model.Product;
import de.jonashackt.model.User;
import de.jonashackt.model.Weather;

public final class GetCityForecastByZIPInMapper {

	// private Constructor for Utility-Class
	private GetCityForecastByZIPInMapper() {};
	
	public static Weather mapRequest2Weather(ForecastRequest forecastRequest) {
		Weather weather = new Weather();
		weather.setPostalCode(forecastRequest.getZIP());
		weather.setFlagColor(forecastRequest.getFlagcolor());
		weather.setProduct(mapProduct(forecastRequest.getProductName()));		
		weather.setUser(mapUser(forecastRequest.getForecastCustomer()));
		return weather;
	}

    private static User mapUser(ForecastCustomer forecastCustomer) {
        User user = new User();
		user.setAge(forecastCustomer.getAge());
		user.setContribution(forecastCustomer.getContribution());
		user.setMethodOfPayment(mapPayment(forecastCustomer.getMethodOfPayment()));
        return user;
    }
	
	private static Product mapProduct(ProductName productName) {
		if(productName == null) {
			return Product.Unknown;
		}
		else if(Product.ForecastBasic.getName().equals(productName.value())) {
            return Product.ForecastBasic;
        } else if (Product.ForecastProfessional.getName().equals(productName.value())) {
            return Product.ForecastProfessional;
        } else if (Product.ForecastUltimateXL.equals(productName.value())) {
            return Product.ForecastUltimateXL;
        } else {
            return Product.Unknown;
        }
    }

    private static MethodOfPayment mapPayment(String methodOfPayment) {
	    if(MethodOfPayment.Paypal.getName().equals(methodOfPayment)) {
	        return MethodOfPayment.Paypal;
	    } else if (MethodOfPayment.Bitcoin.getName().equals(methodOfPayment)) {
	        return MethodOfPayment.Bitcoin;
	    } else {
	        return MethodOfPayment.Unknown;
	    }
	}
}
