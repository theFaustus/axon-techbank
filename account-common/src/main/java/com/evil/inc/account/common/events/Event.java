package com.evil.inc.account.common.events;

import com.evil.inc.cqrs.core.domain.AggregateId;
import com.evil.inc.cqrs.core.messages.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Event {
    private String id;
    private long version;
    private LocalDateTime systemCaptureDateTime = LocalDateTime.now();

    public String getType() {
        return getClass().getSimpleName();
    }
}
