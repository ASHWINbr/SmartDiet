package service;

import java.util.List;
import model.UserDietPojo;
import model.UserPojo;

public interface MethodInterface {

	boolean saveUser(UserPojo userPojo);

	String loginUser(String email, String password);

	boolean saveDietPlan(UserDietPojo userDietPojo);

	List<UserPojo> getUserDetails(String email);

	List<UserDietPojo> getDietPlan(String email);

}
