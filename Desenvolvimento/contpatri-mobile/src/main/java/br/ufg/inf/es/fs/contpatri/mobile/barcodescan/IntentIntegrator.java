/*
 * Copyright 2009 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.ufg.inf.es.fs.contpatri.mobile.barcodescan;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/**
 * <p>
 * A utility class which helps ease integration with Barcode Scanner via
 * {@link Intent}s. This is a simple way to invoke barcode scanning and receive
 * the result, without any need to integrate, modify, or learn the project's
 * source code.
 * </p>
 * 
 * <h2>Initiating a barcode scan</h2>
 * 
 * <p>
 * To integrate, create an instance of {@code IntentIntegrator} and call
 * {@link #initiateScan()} and wait for the result in your app.
 * </p>
 * 
 * <p>
 * It does require that the Barcode Scanner (or work-alike) application is
 * installed. The {@link #initiateScan()} method will prompt the user to
 * download the application, if needed.
 * </p>
 * 
 * <p>
 * There are a few steps to using this integration. First, your {@link Activity}
 * must implement the method {@link Activity#onActivityResult(int, int, Intent)}
 * and include a line of code like this:
 * </p>
 * 
 * <pre>
 * {@code
 * public void onActivityResult(int requestCode, int resultCode, Intent intent) {
 *   IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
 *   if (scanResult != null) {
 *     // handle scan result
 *   }
 *   // else continue with any other code you need in the method
 *   ...
 * }
 * }
 * </pre>
 * 
 * <p>
 * This is where you will handle a scan result.
 * </p>
 * 
 * <p>
 * Second, just call this in response to a user action somewhere to begin the
 * scan process:
 * </p>
 * 
 * <pre>
 * {
 * 	&#064;code
 * 	IntentIntegrator integrator = new IntentIntegrator(yourActivity);
 * 	integrator.initiateScan();
 * }
 * </pre>
 * 
 * <p>
 * Note that {@link #initiateScan()} returns an {@link AlertDialog} which is
 * non-null if the user was prompted to download the application. This lets the
 * calling app potentially manage the dialog. In particular, ideally, the app
 * dismisses the dialog if it's still active in its {@link Activity#onPause()}
 * method.
 * </p>
 * 
 * <p>
 * You can use {@link #setTitle(String)} to customize the title of this download
 * prompt dialog (or, use {@link #setTitleByID(int)} to set the title by string
 * resource ID.) Likewise, the prompt message, and yes/no button labels can be
 * changed.
 * </p>
 * 
 * <p>
 * Finally, you can use {@link #addExtra(String, Object)} to add more parameters
 * to the Intent used to invoke the scanner. This can be used to set additional
 * options not directly exposed by this simplified API.
 * </p>
 * 
 * <p>
 * By default, this will only allow applications that are known to respond to
 * this intent correctly do so. The apps that are allowed to response can be set
 * with {@link #setTargetApplications(List)}. For example, set to
 * {@link #TARGET_BARCODE_SCANNER_ONLY} to only target the Barcode Scanner app
 * itself.
 * </p>
 * 
 * <h2>Sharing text via barcode</h2>
 * 
 * <p>
 * To share text, encoded as a QR Code on-screen, similarly, see
 * {@link #shareText(CharSequence)}.
 * </p>
 * 
 * <p>
 * Some code, particularly download integration, was contributed from the
 * Anobiit application.
 * </p>
 * 
 * <h2>Enabling experimental barcode formats</h2>
 * 
 * <p>
 * Some formats are not enabled by default even when scanning with
 * {@link #ALL_CODE_TYPES}, such as PDF417. Use
 * {@link #initiateScan(java.util.Collection)} with a collection containing the
 * names of formats to scan for explicitly, like "PDF_417", to use such formats.
 * </p>
 * 
 * @author Sean Owen
 * @author Fred Lin
 * @author Isaac Potoczny-Jones
 * @author Brad Drehmer
 * @author gcstang
 */
public class IntentIntegrator {

	/*
	 * Only use bottom 16 bits
	 */
	public static final int REQUEST_CODE = 0x0000c0de;

	private static final String TAG = IntentIntegrator.class.getSimpleName();

	public static final String DEFAULT_TITLE = "Instalar Barcode Scanner?";
	public static final String DEFAULT_MESSAGE = "Este aplicativo requer o Barcode Scanner para ser instalado, deseja instalar?";
	public static final String DEFAULT_YES = "Sim";
	public static final String DEFAULT_NO = "Não";

	private static final String BS_PACKAGE = "com.google.zxing.client.android";
	private static final String BSPLUS_PACKAGE = "com.srowen.bs.android";

	// supported barcode formats
	public static final Collection<String> PRODUCT_CODE_TYPES = list("UPC_A",
			"UPC_E", "EAN_8", "EAN_13", "RSS_14");
	public static final Collection<String> ONE_D_CODE_TYPES = list("UPC_A",
			"UPC_E", "EAN_8", "EAN_13", "CODE_39", "CODE_93", "CODE_128",
			"ITF", "RSS_14", "RSS_EXPANDED");
	public static final Collection<String> QR_CODE_TYPES = Collections
			.singleton("QR_CODE");
	public static final Collection<String> DATA_MATRIX_TYPES = Collections
			.singleton("DATA_MATRIX");

	public static final Collection<String> ALL_CODE_TYPES = null;

	public static final List<String> TARGET_BARCODE_SCANNER_ONLY = Collections
			.singletonList(BS_PACKAGE);
	/*
	 * Barcode Scanner+
	 */
	public static final List<String> TARGET_ALL_KNOWN = list(BSPLUS_PACKAGE,
	/*
	 * Barcode Scanner+ Simple
	 */
	BSPLUS_PACKAGE + ".simple",
	/*
	 * Barcode Scanner. What else supports this intent?
	 */
	BS_PACKAGE);

	private static boolean contains(final Iterable<ResolveInfo> availableApps,
			final String targetApp) {
		for (final ResolveInfo availableApp : availableApps) {
			final String packageName = availableApp.activityInfo.packageName;
			if (targetApp.equals(packageName)) {
				return true;
			}
		}
		return false;
	}
	private static List<String> list(final String... values) {
		return Collections.unmodifiableList(Arrays.asList(values));
	}
	/**
	 * <p>
	 * Call this from your {@link Activity}'s
	 * {@link Activity#onActivityResult(int, int, Intent)} method.
	 * </p>
	 * 
	 * @return null if the event handled here was not related to this class, or
	 *         else an {@link IntentResult} containing the result of the scan.
	 *         If the user cancelled scanning, the fields will be null.
	 */
	public static IntentResult parseActivityResult(final int requestCode,
			final int resultCode, final Intent intent) {
		if (requestCode == REQUEST_CODE) {
			if (resultCode == Activity.RESULT_OK) {
				final String contents = intent.getStringExtra("SCAN_RESULT");
				final String formatName = intent
						.getStringExtra("SCAN_RESULT_FORMAT");
				final byte[] rawBytes = intent
						.getByteArrayExtra("SCAN_RESULT_BYTES");
				final int intentOrientation = intent.getIntExtra(
						"SCAN_RESULT_ORIENTATION", Integer.MIN_VALUE);
				final Integer orientation = intentOrientation == Integer.MIN_VALUE ? null
						: intentOrientation;
				final String errorCorrectionLevel = intent
						.getStringExtra("SCAN_RESULT_ERROR_CORRECTION_LEVEL");
				return new IntentResult(contents, formatName, rawBytes,
						orientation, errorCorrectionLevel);
			}
			return null;
		}
		return null;
	}
	private final Activity activity;
	private String title;
	private String message;
	private String buttonYes;

	private String buttonNo;

	private List<String> targetApplications;

	private final Map<String, Object> moreExtras;

	public IntentIntegrator(final Activity activity) {
		this.activity = activity;
		title = DEFAULT_TITLE;
		message = DEFAULT_MESSAGE;
		buttonYes = DEFAULT_YES;
		buttonNo = DEFAULT_NO;
		targetApplications = TARGET_ALL_KNOWN;
		moreExtras = new HashMap<String, Object>(3);
	}

	public final void addExtra(final String key, final Object value) {
		moreExtras.put(key, value);
	}

	private void attachMoreExtras(final Intent intent) {
		for (final Map.Entry<String, Object> entry : moreExtras.entrySet()) {
			final String key = entry.getKey();
			final Object value = entry.getValue();
			// Kind of hacky
			if (value instanceof Integer) {
				intent.putExtra(key, (Integer) value);
			} else if (value instanceof Long) {
				intent.putExtra(key, (Long) value);
			} else if (value instanceof Boolean) {
				intent.putExtra(key, (Boolean) value);
			} else if (value instanceof Double) {
				intent.putExtra(key, (Double) value);
			} else if (value instanceof Float) {
				intent.putExtra(key, (Float) value);
			} else if (value instanceof Bundle) {
				intent.putExtra(key, (Bundle) value);
			} else {
				intent.putExtra(key, value.toString());
			}
		}
	}

	private String findTargetAppPackage(final Intent intent) {
		final PackageManager pm = activity.getPackageManager();
		final List<ResolveInfo> availableApps = pm.queryIntentActivities(
				intent, PackageManager.MATCH_DEFAULT_ONLY);
		if (availableApps != null) {
			for (final String targetApp : targetApplications) {
				if (contains(availableApps, targetApp)) {
					return targetApp;
				}
			}
		}
		return null;
	}

	public String getButtonNo() {
		return buttonNo;
	}

	public String getButtonYes() {
		return buttonYes;
	}

	public String getMessage() {
		return message;
	}

	public Map<String, ?> getMoreExtras() {
		return moreExtras;
	}

	public Collection<String> getTargetApplications() {
		return targetApplications;
	}

	public String getTitle() {
		return title;
	}

	/**
	 * Initiates a scan for all known barcode types.
	 */
	public final AlertDialog initiateScan() {
		return initiateScan(ALL_CODE_TYPES);
	}

	/**
	 * Initiates a scan only for a certain set of barcode types, given as
	 * strings corresponding to their names in ZXing's {@code BarcodeFormat}
	 * class like "UPC_A". You can supply constants like
	 * {@link #PRODUCT_CODE_TYPES} for example.
	 * 
	 * @return the {@link AlertDialog} that was shown to the user prompting them
	 *         to download the app if a prompt was needed, or null otherwise
	 */
	public final AlertDialog initiateScan(
			final Collection<String> desiredBarcodeFormats) {
		final Intent intentScan = new Intent(BS_PACKAGE + ".SCAN");
		intentScan.addCategory(Intent.CATEGORY_DEFAULT);

		// check which types of codes to scan for
		if (desiredBarcodeFormats != null) {
			// set the desired barcode types
			final StringBuilder joinedByComma = new StringBuilder();
			for (final String format : desiredBarcodeFormats) {
				if (joinedByComma.length() > 0) {
					joinedByComma.append(',');
				}
				joinedByComma.append(format);
			}
			intentScan.putExtra("SCAN_FORMATS", joinedByComma.toString());
		}

		final String targetAppPackage = findTargetAppPackage(intentScan);
		if (targetAppPackage == null) {
			return showDownloadDialog();
		}
		intentScan.setPackage(targetAppPackage);
		intentScan.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intentScan.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
		attachMoreExtras(intentScan);
		startActivityForResult(intentScan, REQUEST_CODE);
		return null;
	}

	public void setButtonNo(final String buttonNo) {
		this.buttonNo = buttonNo;
	}

	public void setButtonNoByID(final int buttonNoID) {
		buttonNo = activity.getString(buttonNoID);
	}

	public void setButtonYes(final String buttonYes) {
		this.buttonYes = buttonYes;
	}

	public void setButtonYesByID(final int buttonYesID) {
		buttonYes = activity.getString(buttonYesID);
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public void setMessageByID(final int messageID) {
		message = activity.getString(messageID);
	}

	public void setSingleTargetApplication(final String targetApplication) {
		targetApplications = Collections.singletonList(targetApplication);
	}

	public final void setTargetApplications(
			final List<String> targetApplications) {
		if (targetApplications.isEmpty()) {
			throw new IllegalArgumentException("No target applications");
		}
		this.targetApplications = targetApplications;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public void setTitleByID(final int titleID) {
		title = activity.getString(titleID);
	}

	/**
	 * Defaults to type "TEXT_TYPE".
	 * 
	 * @see #shareText(CharSequence, CharSequence)
	 */
	public final AlertDialog shareText(final CharSequence text) {
		return shareText(text, "TEXT_TYPE");
	}

	/**
	 * Shares the given text by encoding it as a barcode, such that another user
	 * can scan the text off the screen of the device.
	 * 
	 * @param text
	 *            the text string to encode as a barcode
	 * @param type
	 *            type of data to encode. See
	 *            {@code com.google.zxing.client.android.Contents.Type}
	 *            constants.
	 * @return the {@link AlertDialog} that was shown to the user prompting them
	 *         to download the app if a prompt was needed, or null otherwise
	 */
	public final AlertDialog shareText(final CharSequence text,
			final CharSequence type) {
		final Intent intent = new Intent();
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		intent.setAction(BS_PACKAGE + ".ENCODE");
		intent.putExtra("ENCODE_TYPE", type);
		intent.putExtra("ENCODE_DATA", text);
		final String targetAppPackage = findTargetAppPackage(intent);
		if (targetAppPackage == null) {
			return showDownloadDialog();
		}
		intent.setPackage(targetAppPackage);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
		attachMoreExtras(intent);
		activity.startActivity(intent);
		return null;
	}

	private AlertDialog showDownloadDialog() {
		final AlertDialog.Builder downloadDialog = new AlertDialog.Builder(
				activity);
		downloadDialog.setTitle(title);
		downloadDialog.setMessage(message);
		downloadDialog.setPositiveButton(buttonYes,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(final DialogInterface dialogInterface,
							final int i) {
						final String packageName = targetApplications.get(0);
						final Uri uri = Uri.parse("market://details?id="
								+ packageName);
						final Intent intent = new Intent(Intent.ACTION_VIEW,
								uri);
						try {
							activity.startActivity(intent);
						} catch (final ActivityNotFoundException anfe) {
							// Hmm, market is not installed
							Log.w(TAG,
									"Google Play is not installed; cannot install "
											+ packageName);
						}
					}
				});
		downloadDialog.setNegativeButton(buttonNo,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(final DialogInterface dialogInterface,
							final int i) {
					}
				});
		return downloadDialog.show();
	}

	/**
	 * Start an activity. This method is defined to allow different methods of
	 * activity starting for newer versions of Android and for compatibility
	 * library.
	 * 
	 * @param intent
	 *            Intent to start.
	 * @param code
	 *            Request code for the activity
	 * @see android.app.Activity#startActivityForResult(Intent, int)
	 * @see android.app.Fragment#startActivityForResult(Intent, int)
	 */
	protected void startActivityForResult(final Intent intent, final int code) {
		activity.startActivityForResult(intent, code);
	}

}