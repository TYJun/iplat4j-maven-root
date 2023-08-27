package com.bonawise.smp.base.domain;

/**
 * @desc 
 * @Date 2018年4月9日 11点22分
 * @author yaokang
 *
 */
public class MealOrderTypeEntity {

	/**
	 * 病患普餐 -- POS机
	 */
	public static final String MEAL_PATIENT_POS = "mealPatientPOS";
	
	/**
	 * 病患普餐 -- APP
	 */
	public static final String MEAL_PATIENT_APP = "mealPatientAPP";
	
	/**
	 * 职工普餐
	 */
	public static final String MEAL_WORK_APP = "mealWORKAPP";
	
	/**
	 * 病患套餐 -- APP
	 */
	public static final String COMBO_PATIENT_APP = "ComboPatientAPP";
	
	/**
	 * 病患套餐 -- POS机
	 */
	public static final String COMBO_PATIENT_POS = "ComboPatientPOS";
	
	/**
	 * 职工套餐
	 */
	public static final String COMBO_WORK_APP = "ComboWORKAPP";
}
