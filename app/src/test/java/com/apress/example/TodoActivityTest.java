package com.apress.example;

import android.app.Activity;
import android.os.Build;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class TodoActivityTest
{
	@Test
	public void should_add_new_task() throws Exception
	{
		final TodoActivity activity = Robolectric.buildActivity(TodoActivity.class).create().get();

		activity.getProvider().addTask("Some task");
		final List<String> tasks = activity.getProvider().findAll();
		Assert.assertEquals(tasks.size(), 1);
	}

	@Test
	public void should_add_task_using_ux() throws Exception
	{
		final TodoActivity activity = Robolectric.buildActivity(TodoActivity.class).create().get();

		activity.getEditableTextbox().setText("My task");
		activity.getSaveTaskButton().performClick();

		final int tasks = activity.getTaskListView().getCount();
		Assert.assertEquals(tasks, 4);
	}

	@Test
	public void should_create_activity() throws Exception
	{
		final Activity activity = Robolectric.buildActivity(TodoActivity.class).create().get();
		Assert.assertTrue(activity != null);
	}

	@Test
	public void should_find_tasks() throws Exception
	{
		final TodoActivity activity = Robolectric.buildActivity(TodoActivity.class).create().get();

		activity.getProvider().addTask("Some task 1");
		activity.getProvider().addTask("Some task 2");
		activity.getProvider().addTask("Some task 3");
		final List<String> tasks = activity.getProvider().findAll();
		Assert.assertEquals(tasks.size(), 3);
	}

	@Test
	public void should_remove_task() throws Exception
	{
		final TodoActivity activity = Robolectric.buildActivity(TodoActivity.class).create().get();

		activity.getProvider().addTask("Some task");
		activity.getProvider().deleteTask("Some task");
		final List<String> tasks = activity.getProvider().findAll();
		Assert.assertEquals(tasks.size(), 0);
	}
}
