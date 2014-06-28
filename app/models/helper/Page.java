package models.helper;

import java.util.List;

/**
 * Provides generic pagination functionalities on a basic level
 * @author Markus Ziller
 *
 * @param <T>
 */
public class Page<T> {
	
	private List<T> allElements = null;
	private List<T> currentScope = null;
	private Integer currentPage = null;
	public Integer getCurrentPage() {
		return currentPage;
	}

	private Integer currentScopeStartIndex = 0;
	private Integer itemsPP = null;
	
	public Page(List<T> elements, Integer page, Integer ipp){
		
		this.allElements = elements;
		this.itemsPP = ipp;
		if(page < 1){
			this.currentPage = 1;
		}
		else{
			this.currentPage = page;
			
		}
		
		if(currentPage >= getTotalPageCount()){
			this.currentScope = allElements.subList((currentPage-1) * itemsPP, allElements.size());
		}else{
			this.currentScope = allElements.subList((currentPage-1) * itemsPP, currentPage * itemsPP);
		}
		
		
		
		
	}
	
	public T get(Integer i){
		return currentScope.get(i);
	}
	
	public List<T> getList(){
		return currentScope;
	}
	
	public Integer getPageIndex(){
		return currentScopeStartIndex / itemsPP;
	}
	
	public Integer getTotalPageCount(){
		return (int) Math.ceil((1.0 * allElements.size()) / (1.0 * itemsPP));
	}
	
	public boolean hasPrev(){
		return currentPage > 1;
	}
	
	public boolean hasNext(){
		return currentPage < getTotalPageCount();
	}
	
	
	/**
	 * .hasPrev) {
    <li><a href="@routes.Application.startPaginate(offers.getPageIndex - 1)">Vorherige Seite</a></li>
    }
    @for(j <- 0 to  offers.getTotalPageCount()-1){
    <li @if(j == offers.getPageIndex()){class="active"}>
      <a href="@routes.Application.startPaginate(j)">@(j+1)</a>
    </li>}
      @if(offers.hasNext) {
	 * @author Markus
	 * @date 12.01.2013
	 * @return
	 */
	
	public Integer getTotalRowCount(){
		return allElements.size();
	}
	
	public List<T> getPage(Integer page){
		
		if(page > getTotalPageCount()){
			return null;
		}
		if(page == getTotalPageCount()){
			return allElements.subList(page * itemsPP, allElements.size()-1);
		}
		
		return allElements.subList(page * itemsPP, page+1 * itemsPP - 1);
		
	}

}
