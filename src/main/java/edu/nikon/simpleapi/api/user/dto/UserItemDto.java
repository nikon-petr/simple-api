package edu.nikon.simpleapi.api.user.dto;

public class UserItemDto {

    private final long id;
    private final String firstName;
    private final String secondName;
    private final String middleName;
    private final String position;

    public UserItemDto(long id, String firstName, String secondName, String middleName, String position) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserItemDto{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", secondName='").append(secondName).append('\'');
        sb.append(", middleName='").append(middleName).append('\'');
        sb.append(", position='").append(position).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
