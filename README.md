# Pagination in Android

 - create a project and implement recyclerView in your Activity.
 - Now modify your recyclerView to implement Pagination.

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            mainBinding.recycler.setLayoutManager(layoutManager);
            simpleAdapter = new SimpleAdapter(list);
            mainBinding.recycler.setAdapter(simpleAdapter);

            mainBinding.recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentItems = layoutManager.getChildCount();
                scrolledOutItems = layoutManager.findFirstVisibleItemPosition();
                totalItems = layoutManager.getItemCount();

                if (isScrolling && currentItems + scrolledOutItems == totalItems) {
                    // Fetch Data
                    isScrolling = false;
                    fetchData();
                }
            }
        });
![Screenshot 2024-03-07 171529](https://github.com/ArjunGupta08/Pagination-Android-Java/assets/85922120/7d098360-fa46-47b0-9c07-5870a62a35c4)
