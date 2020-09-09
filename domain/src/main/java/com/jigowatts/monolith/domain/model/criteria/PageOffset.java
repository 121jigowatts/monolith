package com.jigowatts.monolith.domain.model.criteria;

import lombok.Getter;

@Getter
public class PageOffset {
    private int value;
    private static final String NAME = "page_offset";

    public PageOffset(String value) {
        if (value == null || value.isEmpty()) {
            this.value = 1;
        } else {
            try {
                this.value = Integer.parseInt(value);
            } catch (Exception e) {
                throw new IllegalArgumentException(NAME + "=" + value);
            }
        }
    }
}
