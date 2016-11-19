package bbu.com.smartoffice.contract;

import bbu.com.smartoffice.base.BaseModel;
import bbu.com.smartoffice.base.BasePresenter;
import bbu.com.smartoffice.base.BaseView;
import rx.Observable;

/**
 * Created by G on 2016/11/19 0019.
 */

public interface WebViewContract {
    interface View extends BaseView {
    }

    interface Model extends BaseModel {

    }

    abstract class Presenter extends BasePresenter<View, Model> {
    }

}
