package com.example.bookshelf;

import java.util.List;

public class PaginatedResponse<T> {

    private int totalPages;
    private long totalElements;
    private int pageNumber;
    private List<T> elements;

    public PaginatedResponse() {
    }

    public PaginatedResponse(int totalPages, long totalElements, int pageNumber, List<T> elements) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.pageNumber = pageNumber;
        this.elements = elements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }
}

