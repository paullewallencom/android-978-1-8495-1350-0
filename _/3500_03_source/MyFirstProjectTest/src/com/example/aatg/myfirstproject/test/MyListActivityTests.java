package com.example.aatg.myfirstproject.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.ListView;

import com.example.aatg.myfirstproject.MyListActivity;

public class MyListActivityTests extends
		ActivityInstrumentationTestCase2<MyListActivity> {

	private MyListActivity mActivity;
	private ListView mListView;

	public MyListActivityTests() {
		this("MyListActivityTests");
	}
	
	public MyListActivityTests(String name) {
		super(MyListActivity.class);
		setName(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		mActivity = getActivity();
		mListView = mActivity.getListView();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testListScrolling() {
		mListView.scrollTo(0, 0);
		TouchUtils.dragQuarterScreenUp(this, mActivity);
		TouchUtils.dragQuarterScreenUp(this, mActivity);
		TouchUtils.dragQuarterScreenUp(this, mActivity);
		TouchUtils.dragQuarterScreenUp(this, mActivity);
		TouchUtils.tapView(this, mListView);

		final int expectedItemPosition = 6;
		final int actualItemPosition = mListView.getFirstVisiblePosition();
		assertEquals("Wrong position", expectedItemPosition, actualItemPosition);
		
		final String expected = "Anguilla";
		final String actual = mListView.getAdapter().getItem(expectedItemPosition).toString();
		assertEquals("Wrong content", actual, expected);
	}
}
