package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "diet")
public class UserDietPojo implements Serializable {
	private String email, height, weight, age, gender, bmi, category, plan,
			type;
	@Column(name = "weight_to_be")
	String weightToBe;
	@Id
	@GeneratedValue
	private long id;

	public UserDietPojo(String email, String height, String weight, String age,
			String gender, String bmi, String category, String weightToBe,
			String plan, String type) {
		this.email = email;
		this.height = height;
		this.weight = weight;
		this.age = age;
		this.gender = gender;
		this.bmi = bmi;
		this.category = category;
		this.weightToBe = weightToBe;
		this.plan = plan;
		this.type = type;
	}

	public UserDietPojo() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBmi() {
		return bmi;
	}

	public void setBmi(String bmi) {
		this.bmi = bmi;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWeightToBe() {
		return weightToBe;
	}

	public void setWeightToBe(String weightToBe) {
		this.weightToBe = weightToBe;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
