package bg.tu_sofia.pmu.project.testsystem.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import bg.tu_sofia.pmu.project.testsystem.R;
import bg.tu_sofia.pmu.project.testsystem.persistence.datasources.ResultsDataSource;
import bg.tu_sofia.pmu.project.testsystem.persistence.model.Result;
import bg.tu_sofia.pmu.project.testsystem.utils.CacheControler;
import bg.tu_sofia.pmu.project.testsystem.utils.Test;
import bg.tu_sofia.pmu.project.testsystem.utils.TestSystemConstants;

public class ResultsListActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_list);

        init();
    }

    private void init() {
        ResultsDataSource rds = new ResultsDataSource(ResultsListActivity.this);
        ArrayList<Result> results = null;

        String user = getIntent().getExtras().getString(TestSystemConstants.USER_KEY);

        if (TestSystemConstants.ALL_USERS.equals(user))
            results = rds.getTestResultsForAllUsers();
        else
            results = rds.getTestResultsPerUser(user);

        Button printButton;
        Button homeButton;

        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(ResultsListActivity.this, results));

        printButton = (Button) findViewById(R.id.print_button);
        printButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = takeScreenshot();
                saveBitmap(bitmap);
            }
        });

        homeButton = (Button) findViewById(R.id.home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultsListActivity.this.finish();
            }
        });
    }

    private Bitmap takeScreenshot() {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    private void saveBitmap(Bitmap bitmap) {
        File imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }

    private void saveResultToDB() {
        Test test = CacheControler.getInstance().getCurrentTest();
        Result result = new Result(CacheControler.getInstance().getUser().getUsername(),
                test.getCategory(),
                test.getCorrectAnswers(),
                test.getWrongAnswers(),
                test.getTakenOn());
        ResultsDataSource rds = new ResultsDataSource(ResultsListActivity.this);
        rds.insertTestResult(result);
    }
}

class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<Result> results = null;
    private static LayoutInflater inflater=null;

    public CustomAdapter(Context context, ArrayList<Result> results) {
        this.context = context;
        this.results = results;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.list_row_layout, null);
        holder.cat = (TextView) rowView.findViewById(R.id.resTestCat);
        holder.user = (TextView) rowView.findViewById(R.id.resTestUser);
        holder.img = (ImageView) rowView.findViewById(R.id.resTestImage);

        holder.cat.setText(results.get(position).getCategory());
        holder.user.setText(results.get(position).getUser());

        int percentage;
        try {
            percentage = (results.get(position).getCorrectAnswers() * 100) / (results.get(position).getCorrectAnswers() + results.get(position).getWrongAnswers());
        } catch (ArithmeticException ae) {
            percentage = 0;
        }

        if (percentage > 50f)
            holder.img.setImageResource(R.drawable.ok_icon);
        else
            holder.img.setImageResource(R.drawable.cross_icon);

        return rowView;

    }

    public class Holder
    {
        TextView cat;
        TextView user;
        ImageView img;
    }

}