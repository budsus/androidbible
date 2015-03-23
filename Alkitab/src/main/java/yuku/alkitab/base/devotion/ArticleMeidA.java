package yuku.alkitab.base.devotion;

import android.net.Uri;
import android.text.Html;
import android.text.SpannableStringBuilder;
import yuku.alkitab.base.ac.DevotionActivity;
import yuku.alkitab.base.widget.CallbackSpan;

public class ArticleMeidA extends DevotionArticle {
	public static final String TAG = ArticleMeidA.class.getSimpleName();
	private String date;
	private String bodyHtml;
	private boolean readyToUse;

	public ArticleMeidA(String date) {
		this.date = date;
	}

	public ArticleMeidA(String date, String bodyHtml, boolean readyToUse) {
		this.date = date;
		this.bodyHtml = bodyHtml;
		this.readyToUse = readyToUse;
	}

	@Override
	public String getDate() {
		return date;
	}

	@Override
	public boolean getReadyToUse() {
		return readyToUse;
	}

	@Override
	public DevotionActivity.DevotionKind getKind() {
		return DevotionActivity.DevotionKind.MEID_A;
	}

	@Override
	public void fillIn(String raw) {
		bodyHtml = raw;
		readyToUse = !raw.startsWith("NG");
	}

	@Override
	public CharSequence getContent(CallbackSpan.OnClickListener<String> verseClickListener) {
		SpannableStringBuilder sb = new SpannableStringBuilder();
		sb.append(Html.fromHtml(bodyHtml + "<br/><small><a href='patchtext://host/?referenceUrl=" + Uri.encode("http://m.ccel.org/ccel/spurgeon/morneve.d" + date.substring(4, 8) + "am.html") + "'>Kirim perbaikan</a></small>"));

		convertLinks(sb, verseClickListener);

		return sb;
	}

	@Override
	public String[] getHeaderTitleBody() {
		return new String[]{null, null, bodyHtml};
	}
}
