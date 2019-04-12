package com.example.mpd_cwk_S1512251.RSS;

// Name                 Gavin Walter Jaap
// Student ID           S1512251
// Programme of Study   Computing

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mpd_cwk_S1512251.DataObject.Article;
import com.example.mpd_cwk_S1512251.MapsActivity;
import com.example.mpd_cwk_S1512251.R;
import com.example.mpd_cwk_S1512251.UI.MyAdapter;
import com.example.mpd_cwk_S1512251.UI.MyViewHolder;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

class RSSParser extends AsyncTask<Void,Void,Boolean> implements SearchView.OnQueryTextListener,MyViewHolder.OnNoteListener {
    Context c;
    InputStream is;
    RecyclerView rv;
    int count;

    ProgressDialog pd;
    ArrayList<Article> articles=new ArrayList<>();

    public RSSParser(Context c, InputStream is, RecyclerView rv){
        this.c =c;
        this.is =is;
        this.rv =rv;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("Fetch Headline");
        pd.setMessage("Fetching.....Please wait");
        pd.show();
    }
    @Override
    protected Boolean doInBackground(Void... params) {
        return this.parseRSS();
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    @Override
    protected void onPostExecute(Boolean isParsed){
        super.onPostExecute(isParsed);

        pd.dismiss();

        if (isParsed)
        {
            //bind
            rv.setAdapter(new MyAdapter(c,articles, this));


        }else{
            Toast.makeText(c,"Unable to Parse", Toast.LENGTH_SHORT).show();
        }
    }
    private Boolean parseRSS(){
        try {
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            XmlPullParser parser=factory.newPullParser();

            parser.setInput(is,null);
            int event=parser.getEventType();

            String tagValue=null;
            Boolean isSiteMeta=true;

            articles.clear();
            Article article=new Article();

            do {
                String tagName=parser.getName();
                switch (event){
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase("item")){
                            article=new Article();
                            isSiteMeta=false;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        tagValue=parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (!isSiteMeta) {
                            if (tagName.equalsIgnoreCase("title")) {
                                article.setTitle(tagValue);
                            } else if (tagName.equalsIgnoreCase("description")) {
                                String desc=tagValue;
                                String location=desc.substring(desc.indexOf("Location")+10,desc.indexOf("Lat")-2);
                                String depth=desc.substring(desc.indexOf("Depth")+7,desc.indexOf("Mag")-2);
                                String magnitude=desc.substring(desc.indexOf("Magnitude: ")+12);

                                article.setLocation(location);
                                article.setDepth(depth);
                                article.setMagnitude(magnitude);
                            } else if (tagName.equalsIgnoreCase("link")) {
                                article.setLink(tagValue);
                            } else if (tagName.equalsIgnoreCase("pubDate")) {
                                article.setDate(tagValue);
                            } else if (tagName.equalsIgnoreCase("geo:lat")) {
                                article.setLatitude(tagValue);
                            } else if (tagName.equalsIgnoreCase("geo:long")) {
                                article.setLongitude(tagValue);
                            }
                        }
                        if (tagName.equalsIgnoreCase("item"))
                        {
                            articles.add(article);
                            isSiteMeta=true;
                        }
                        break;
                }
                    event=parser.next();



            }while (event != XmlPullParser.END_DOCUMENT);

            return true;

        } catch (XmlPullParserException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuItem searchItem = menu.findItem(R.id.action_search);
        return true;
    }
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String filteredText) {
        MyAdapter rv = new MyAdapter(c, articles, this);
        filteredText = filteredText.toLowerCase();
        ArrayList<Article> filterList = new ArrayList<>();
        for (Article article : articles) {
            String location = article.getLocation().toLowerCase();
            if (location.contains(filteredText))
                filterList.add(article);

        }
        rv.setFilter(filterList);
        return false;
    }

    @Override
    public void onNoteClick(int position) {
        articles.get(position);
        Intent intent = new Intent(c, MapsActivity.class);
        c.startActivity(intent);
    }
}
