package com.course.auto.framework.model;

import java.util.List;

public class SummaryResult {
    private String token;
    private long foundCount;
    private long abortedCount;
    private long failedCount;
    private long succeededCount;
    private long startedCount;
    private long skippedCount;
    private long timeStarted;
    private long timeFinished;
    private long totalFailureCount;
    private List<FailureInfo> failureInfoList;

    public long getFoundCount() {
        return foundCount;
    }

    public void setFoundCount(long foundCount) {
        this.foundCount = foundCount;
    }

    public long getAbortedCount() {
        return abortedCount;
    }

    public void setAbortedCount(long abortedCount) {
        this.abortedCount = abortedCount;
    }

    public long getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(long failedCount) {
        this.failedCount = failedCount;
    }

    public long getSucceededCount() {
        return succeededCount;
    }

    public void setSucceededCount(long succeededCount) {
        this.succeededCount = succeededCount;
    }

    public long getStartedCount() {
        return startedCount;
    }

    public void setStartedCount(long startedCount) {
        this.startedCount = startedCount;
    }

    public long getSkippedCount() {
        return skippedCount;
    }

    public void setSkippedCount(long skippedCount) {
        this.skippedCount = skippedCount;
    }

    public long getTimeStarted() {
        return timeStarted;
    }

    public void setTimeStarted(long timeStarted) {
        this.timeStarted = timeStarted;
    }

    public long getTimeFinished() {
        return timeFinished;
    }

    public void setTimeFinished(long timeFinished) {
        this.timeFinished = timeFinished;
    }

    public long getTotalFailureCount() {
        return totalFailureCount;
    }

    public void setTotalFailureCount(long totalFailureCount) {
        this.totalFailureCount = totalFailureCount;
    }

    public List<FailureInfo> getFailureInfoList() {
        return failureInfoList;
    }

    public void setFailureInfoList(List<FailureInfo> failureInfoList) {
        this.failureInfoList = failureInfoList;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "SummaryResult{" +
                "foundCount=" + foundCount +
                ", abortedCount=" + abortedCount +
                ", failedCount=" + failedCount +
                ", succeededCount=" + succeededCount +
                ", startedCount=" + startedCount +
                ", skippedCount=" + skippedCount +
                ", timeStarted=" + timeStarted +
                ", timeFinished=" + timeFinished +
                ", totalFailureCount=" + totalFailureCount +
                ", failureInfoList=" + failureInfoList +
                ", token=" + token +
                '}';
    }
}