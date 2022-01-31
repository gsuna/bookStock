package com.gsuna.project.enums;

public enum GenderType implements BaseEnum<Integer> {
	
	MALE(1),FEMALE(2);
	
	private final Integer value;

	GenderType(Integer value) {
        this.value = value;
    }

	@Override
    public Integer getValue() {
        return value;
    }
	
	public static class Converter extends EnumConverter<GenderType, Integer> {
        public Converter() {
            super(GenderType.class);
        }
    }
	
}
