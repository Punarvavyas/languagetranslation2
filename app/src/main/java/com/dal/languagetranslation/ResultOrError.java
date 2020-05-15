package com.dal.languagetranslation;

import androidx.annotation.Nullable;

public class ResultOrError {
    final @Nullable
    String result;
    final @Nullable
    Exception error;

    ResultOrError(@Nullable String result, @Nullable Exception error) {
        this.result = result;
        this.error = error;
    }
}
