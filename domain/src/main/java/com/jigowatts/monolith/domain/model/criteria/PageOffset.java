package com.jigowatts.monolith.domain.model.criteria;

import lombok.Getter;

@Getter
public class PageOffset {
    private int value;

    public PageOffset(String value) {
        if (value == null || value.isEmpty()) {
            this.value = 1;
        } else {
            try {
                this.value = Integer.parseInt(value);
            } catch (Exception e) {
                throw new IllegalArgumentException(value);
            }
        }
    }
}
