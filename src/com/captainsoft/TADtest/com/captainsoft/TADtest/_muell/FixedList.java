package com.captainsoft.TADtest._muell;

import java.util.*;

@SuppressWarnings("serial")
public final class FixedList<T> extends ArrayList<T> {

	private int maxSize = 0;
	
	public FixedList(int maxSize) {
		super();
		this.maxSize = maxSize;
		this.ensureCapacity(this.maxSize);
		
	}
	
	public int getMaxSize() {
		return maxSize;
	}
	
	@Override
	public boolean add(T element) {
		if (this.size() < maxSize) {
			return super.add(element);		
		} else {
			return false;
		}
	}

	@Override
	public void add(int index, T element) {
		if (index >= maxSize) {
			throw new IndexOutOfBoundsException("Index over the max " + maxSize + " size.");
		}
		super.add(index, element);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {		
		return super.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		if (index >= maxSize) {
			throw new IndexOutOfBoundsException("Index over the max " + maxSize + " size.");
		}
		
		return super.addAll(index, c);
	}

	@Override
	public T set(int index, T element) {
		if (index >= maxSize) {
			throw new IndexOutOfBoundsException("Index over the max " + maxSize + " size.");
		}
		return super.set(index, element);
	}
	
	

}
