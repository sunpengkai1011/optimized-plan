package industryproject.mit.deliveryoptimise.network;


import android.content.Context;

import com.android.tu.loadingdialog.LoadingDialog;

import java.util.List;
import java.util.Map;

import industryproject.mit.deliveryoptimise.network.exception.DataFormatException;
import industryproject.mit.deliveryoptimise.network.exception.EmptyDataException;
import industryproject.mit.deliveryoptimise.utils.GeneralUtil;
import industryproject.mit.deliveryoptimise.utils.RetrofitUtil;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import timber.log.Timber;

/**
 * Created by wendychen on 8/12/17.
 */

public abstract class BaseRequest<T, R, M> {

    protected static final String CONTENT_TYPE = "application/json";
    protected static final String CONTENT_TYPE_KEY = "Content-Type";
    protected static final String APPLICATION_HEADER_KEY = "X-Application-Key";
    protected static final String USER_ID_KEY = "X-User-Id";
    protected static final String SESSION_KEY = "X-Session";
    protected static final String SCHEMA_VALUE = "1.0";
    protected static final String FORM_VALUE = "json";

    protected abstract Map<String, String> getHeader();

    protected String getUrl() {
        return "https://evening-eyrie-43949.herokuapp.com";
    }

    protected abstract Class<T> getEndpointClass();

    protected abstract Single<R> getEndpoint(T endpoint);

    protected abstract M dealWithResponse(R response);

    protected PublishSubject<M> observer = PublishSubject.create();

    private T endpoint;

    private LoadingDialog dialog;


    public BaseRequest(Context context) {
        dialog = GeneralUtil.getWaitDialog(context, "waiting");
    }

    public PublishSubject<M> getData() {
        dialog.show();
        endpoint = RetrofitUtil.getService(getHeader(), getUrl(), getEndpointClass());
        getEndpoint(endpoint).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(r -> dealSuccess(r), throwable -> dealError(throwable));
        return observer;
    }

    private void dealSuccess(R response) {
        dialog.dismiss();
        if (response != null) {
            M model = dealWithResponse(response);
            if (model != null) {
                Timber.d("getData success return");
                if (model instanceof List) {
                    List modelList = (List) model;
                    if (modelList.size() <= 0) {
                        Timber.d("getData error: list empty");
                        dealError(new EmptyDataException());
                        return;
                    }
                }
                observer.onNext(model);
            } else {
                Timber.d("getData parse error");
                dealError(new DataFormatException());
            }
        } else {
            Timber.d("getData error");
            dealError(new EmptyDataException());
        }
    }

    private void dealError(Throwable throwable) {
        dialog.dismiss();
        Timber.d(throwable, "getData error");
        observer.onError(throwable);
    }
}
