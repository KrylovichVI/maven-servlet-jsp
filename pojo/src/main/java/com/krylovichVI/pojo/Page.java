package com.krylovichVI.pojo;

public class Page {
 private int pageNumber;
 private int maxPage;

 public Page(int pageNumber, int maxPage){
     this.pageNumber = pageNumber;
     this.maxPage = maxPage;
 }

 public int getFirstPage(){
     return (pageNumber - 1) * maxPage;
 }

 public int getMaxPage(){
     return maxPage;
 }
}
