package com.vkopendoh.orgapp.to;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

import static com.vkopendoh.orgapp.util.EmployeeUtils.RU_NAME_MSG;
import static com.vkopendoh.orgapp.util.EmployeeUtils.RU_NAME_PATTERN;

public class EmplSearchBy {

    @Pattern(regexp = RU_NAME_PATTERN, message = RU_NAME_MSG + "name")
    private String name;
    private LocalDate birthDate;

    public EmplSearchBy() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}

