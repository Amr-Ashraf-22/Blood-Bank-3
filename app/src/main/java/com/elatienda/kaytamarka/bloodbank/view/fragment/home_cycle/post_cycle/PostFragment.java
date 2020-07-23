package com.elatienda.kaytamarka.bloodbank.view.fragment.home_cycle.post_cycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elatienda.kaytamarka.bloodbank.R;
import com.elatienda.kaytamarka.bloodbank.adapter.PostAdapter;
import com.elatienda.kaytamarka.bloodbank.adapter.SpinnerAdapter;
import com.elatienda.kaytamarka.bloodbank.data.model.general_response.GeneralResponse;
import com.elatienda.kaytamarka.bloodbank.data.model.post.Post;
import com.elatienda.kaytamarka.bloodbank.data.model.post.PostData;
import com.elatienda.kaytamarka.bloodbank.util.OnEndLess;
import com.elatienda.kaytamarka.bloodbank.view.fragment.BaseFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.elatienda.kaytamarka.bloodbank.data.api.ApiClient.getClient;

public class PostFragment extends BaseFragment {

    @BindView(R.id.fragment_post_et_search_word)
    EditText fragmentPostEtSearchWord;
    @BindView(R.id.fragment_post_sp_category)
    Spinner fragmentPostSpCategory;
    @BindView(R.id.fragment_post_fab_request_donation)
    FloatingActionButton fragmentPostFabRequestDonation;
    @BindView(R.id.fragment_post_rv_posts)
    RecyclerView fragmentPostRvPosts;

    private SpinnerAdapter categoriesAdapter;
    private PostAdapter postAdapter;
    private List<PostData> postDataList = new ArrayList<>();
    private Integer maxPage = 0;
    private OnEndLess onEndLess;

    public PostFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        //setUpActivity();
        ButterKnife.bind(this, view);

        categoriesAdapter = new SpinnerAdapter(getActivity());
        getClient().getCategories().enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(@NonNull Call<GeneralResponse> call, @NonNull Response<GeneralResponse> response) {
                try {
                    assert response.body() != null;
                    if (response.body().getStatus() == 1) {
                        //spinner.setAdapter(null);
                        fragmentPostSpCategory.setVisibility(View.VISIBLE);
                        categoriesAdapter.setData(response.body().getData(), getResources().getString(R.string.fragment_post_sp_category));
                        fragmentPostSpCategory.setAdapter(categoriesAdapter);
                    } else {
                        Toast.makeText(getActivity(), "onResponse status-0: \n" + response.body().getMsg(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Exception : \n" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<GeneralResponse> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(), "onFailure : \n" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        initRecyclerView();

        return view;
    }

    private void initRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        fragmentPostRvPosts.setHasFixedSize(true);
        fragmentPostRvPosts.setLayoutManager(layoutManager);

        onEndLess = new OnEndLess(layoutManager,1) {
            @Override
            public void onLoadMore(int current_page) {

                if(current_page <= maxPage){
                    if(maxPage != 0 && current_page != 1){
                        onEndLess.previous_page = current_page;
//                        if (Filter){
//                            getPostsFilter(current_page);
//                        }else {
//                            getPost(current_page);
//                        }
                    }else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                }else {
                    onEndLess.current_page = onEndLess.previous_page;
                }

            }
        };
        fragmentPostRvPosts.addOnScrollListener(onEndLess);

        postAdapter = new PostAdapter(getContext(), postDataList);
        fragmentPostRvPosts.setAdapter(postAdapter);
        getPost(1);
    }

    private void getPost(int page) {
        Call<Post> call = getClient().getAllPosts("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27",page);
        startCall(call, page);
    }

    private void startCall(Call<Post> call, int page) {
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(@NonNull Call<Post> call, @NonNull Response<Post> response) {
                try {
                    assert response.body() != null;
                    if (response.body().getStatus() == 1) {
                        maxPage = response.body().getData().getLastPage();
                        postDataList.addAll(response.body().getData().getData());
                        postAdapter.notifyDataSetChanged();
                    }else{
                        Toast.makeText(getActivity(), "onResponse status-0: \n" + response.body().getMsg() , Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                Toast.makeText(getActivity(), "Exception : \n" + e.getMessage() , Toast.LENGTH_LONG).show();
            }
            }

            @Override
            public void onFailure(@NonNull Call<Post> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(), "onFailure : \n" + t.getMessage() , Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnClick(R.id.fragment_post_fab_request_donation)
    public void onViewClicked() {
    }
}
