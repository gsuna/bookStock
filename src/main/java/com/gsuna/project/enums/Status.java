package com.gsuna.project.enums;

public enum Status implements BaseEnum<Integer> {
	
	ACTIVE(1),SUSPEND(2),DELETED(3);
	
	private final Integer value;

	Status(Integer value) {
        this.value = value;
    }

	@Override
    public Integer getValue() {
        return value;
    }
	
	public static class Converter extends EnumConverter<Status, Integer> {
        public Converter() {
            super(Status.class);
        }
    }
	
}
