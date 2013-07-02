package view.product.upload;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import listener.APIListener;
import model.system.InternetTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import services.APIService;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bukalapakmobile.R;

public class UploadProductActivity extends Activity {
	// /GROUP SELECTION
	private static final int GROUP_PICTURE_SELECTION = 1;
	private static final int GROUP_CATEGORY_SELECTION_NODE = 2;
	private static final int GROUP_CATEGORY_SELECTION_CHILD = 5;
	private static final int GROUP_CATEGORY_CANCEL = 3;
	private static final int GROUP_CATEGORY_BACK = 4;
	// /FOR TAKING PICTURE
	private static final int PICK_FROM_SOURCE = 1;
	private static final int PICK_CROP = 2;
	private static final int SELECT_IMAGE_CAMERA = 3;
	private static final int SELECT_IMAGE_GALLERY = 4;
	// for category
	LinkedList<JSONObject> cate_src;
	ArrayList<String> listKategori = new ArrayList<String>();
	
	private Context context;

	//String kategori;
	TextView kategori;
	EditText namaBarang;
	EditText kondisi;
	EditText hargaBarang;
	EditText beratBarang;
	EditText stokBarang;
	EditText deskripsiBarang;
	RadioGroup kondisiGroup;
	RadioButton bekas;
	RadioButton baru;
	String username;
	String password;
	Button image_select;
	Button unggah;
	CheckBox nego;
	CheckBox kurirJNE;
	CheckBox kurirTIKI;
	CheckBox kurirPOS;
	LinearLayout len;
	LinearLayout listImages;
	ArrayList<String> img_ids;
	HashMap<String, String> attribs;
	HashMap<String, View> specs;
	String category_id = "242";
	ImageView imgview;
//	ImageUploadAdapter imageAdapter;
	private Bitmap bitmap;
	// Session Manager Class
	// SessionManager session;
	private APIService api;
	private ServiceConnection mConnection;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.view_product_upload_main);
		cate_src = new LinkedList<JSONObject>();
		img_ids = new ArrayList<String>();
		attribs = new HashMap<String, String>();
		specs = new HashMap<String, View>();
//		imageAdapter = new ImageUploadAdapter(getApplicationContext());
		len = (LinearLayout) findViewById(R.id.listSpecs);
		listImages = (LinearLayout) findViewById(R.id.listImages);
		
		

		

		// session = new SessionManager(getApplicationContext());
		//
		// TextView lblName = (TextView) findViewById(R.id.lblName);
		// TextView lblEmail = (TextView) findViewById(R.id.lblEmail);

		kategori =  (TextView)  findViewById(R.id.kategori_text);
		namaBarang = (EditText) findViewById(R.id.namabarang_edittext);
		bekas = (RadioButton) findViewById(R.id.radioBekas);
		baru = (RadioButton) findViewById(R.id.radioBaru);
		kondisiGroup = (RadioGroup) findViewById(R.id.radioKondisi);
		hargaBarang = (EditText) findViewById(R.id.hargabarang_edittext);
		nego = (CheckBox) findViewById(R.id.checkBisaNego);
		beratBarang = (EditText) findViewById(R.id.perkiraanberat_edittext);
		stokBarang = (EditText) findViewById(R.id.stok_edittext);
		kurirJNE = (CheckBox) findViewById(R.id.checkJasaKurirJNE);
		kurirTIKI = (CheckBox) findViewById(R.id.checkJasaKurirTIKI);
		kurirPOS = (CheckBox) findViewById(R.id.checkJasaKurirPos);
		deskripsiBarang = (EditText) findViewById(R.id.deskripsi_edittext);
		image_select = (Button) findViewById(R.id.photo_button);
		imgview = (ImageView) findViewById(R.id.photo_image);
		unggah = (Button) findViewById(R.id.product_upload_save_button);
		registerForContextMenu(image_select);
		registerForContextMenu(namaBarang);
		ComponentName myService = startService(new Intent(this, APIService.class));
		bindService(new Intent(this, APIService.class), mConnection, BIND_AUTO_CREATE);
//		Intent intent = new Intent(this, APIService.class);
//		bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
		mConnection = new ServiceConnection() {
			@Override
			public void onServiceConnected(ComponentName arg0, IBinder arg1) {
				api = ((APIService.MyBinder) arg1).getService();
				Toast.makeText(getApplicationContext(),
						"Connected to API Service", Toast.LENGTH_SHORT).show();

				try {
					api.retrieveNewAccess("rizkivmaster", "18091992gnome",
							new APIListener() {
								ProgressDialog pd;
								@Override
								public void onSuccess(Object res, Exception e,
										InternetTask task) {
										pd.dismiss();
									api.listCategory(new APIListener() {

										@Override
										public void onSuccess(Object res,
												Exception e, InternetTask task) {
											// TODO Auto-generated method stub
											pd.dismiss();
											cate_src.add((JSONObject) res);
											openContextMenu(namaBarang);
										}

										@Override
										public void onHold(InternetTask task) {
											// TODO Auto-generated method stub
											pd.dismiss();
										}

										@Override
										public void onExecute(InternetTask task) {
											// TODO Auto-generated method stub
											
										}

										@Override
										public void onEnqueue(InternetTask task) {
											// TODO Auto-generated method stub
											pd = new ProgressDialog(context);

					                         pd.setTitle("Kategori");

					                         pd.setMessage("Tunggu sebentar, sedang mengambil kategori...");

					                         pd.setCancelable(false);

					                         pd.setIndeterminate(true);

					                         pd.show();
										}
									});
								}

								@Override
								public void onHold(InternetTask task) {
									// TODO Auto-generated method stub
									pd.dismiss();
								}

								@Override
								public void onExecute(InternetTask task) {
									// TODO Auto-generated method stub

								}

								@Override
								public void onEnqueue(InternetTask task) {
									// TODO Auto-generated method stub
									pd = new ProgressDialog(context);

			                         pd.setTitle("Otentikasi");

			                         pd.setMessage("Tunggu sebentar, sedang otentikasi...");

			                         pd.setCancelable(false);

			                         pd.setIndeterminate(true);

			                         pd.show();
								}
							});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onServiceDisconnected(ComponentName arg0) {
				Toast.makeText(getApplicationContext(),
						"Disconnected from API Service", Toast.LENGTH_SHORT)
						.show();
			}

		};

		image_select.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				try {
					openContextMenu(image_select);
				} catch (ActivityNotFoundException e) {
					// Do nothing for now
				}
			}
		});
		unggah.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				JSONObject obj = null;
				try {
					obj = compileInfo();
				} catch (JSONException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				String imgstr = img_ids.get(0);
				for (int ii = 1; ii < img_ids.size(); ii++) {
					imgstr += "," + img_ids.get(ii);
				}
				try {
					obj.put("images", imgstr);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					try {
						api.createProduct(new APIListener() {

							@Override
							public void onSuccess(Object res, Exception e,
									InternetTask task) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onHold(InternetTask task) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onExecute(InternetTask task) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onEnqueue(InternetTask task) {
								// TODO Auto-generated method stub

							}
						}, obj);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		// registerForContextMenu(unggah);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		if (v.getId() == R.id.photo_button) {
			menu.setHeaderTitle("Pilih sumber");
			menu.add(GROUP_PICTURE_SELECTION, SELECT_IMAGE_GALLERY, 0, "Galeri");
			menu.add(GROUP_PICTURE_SELECTION, SELECT_IMAGE_CAMERA, 1, "Kamera");
		} else if (v == namaBarang) {
			menu.setHeaderTitle("Pilih kategori :");
			@SuppressWarnings("unchecked")
			Iterator<String> iter = cate_src.getLast().keys();
			while (iter.hasNext()) {
				String key = iter.next();
				try {
					cate_src.getLast().getJSONObject(key);
					menu.add(GROUP_CATEGORY_SELECTION_NODE, ContextMenu.NONE,
							ContextMenu.NONE, key);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					String name = null;
					try {
						name = cate_src.getLast().getString(key);
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					menu.add(GROUP_CATEGORY_SELECTION_CHILD, Integer.parseInt(key),
							ContextMenu.NONE, name);
				}
			}
			if(cate_src.size()>1)menu.add(GROUP_CATEGORY_BACK, ContextMenu.NONE, ContextMenu.NONE, "Kembali");
			menu.add(GROUP_CATEGORY_CANCEL, ContextMenu.NONE, ContextMenu.NONE, "Batalkan");
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int groupCode = item.getGroupId();
		int idCode = item.getItemId();
		switch (groupCode) {
		case GROUP_PICTURE_SELECTION:
			switch (idCode) {
			case SELECT_IMAGE_CAMERA:
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra("return-data", true);
				startActivityForResult(intent, PICK_FROM_SOURCE);
				break;
			case SELECT_IMAGE_GALLERY:
				Intent intent2 = new Intent();
				intent2.setType("image/*");
				intent2.setAction(Intent.ACTION_GET_CONTENT);
				intent2.putExtra("return-data", true);
				startActivityForResult(
						Intent.createChooser(intent2, "Complete action using"),
						PICK_FROM_SOURCE);
				break;
			}
			break;
		case GROUP_CATEGORY_SELECTION_NODE:
			try {
				listKategori.add((String) item.getTitle());
				cate_src.add(cate_src.getLast().getJSONObject(
						(String) item.getTitle()));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeContextMenu();
			openContextMenu(namaBarang);
			break;
		case GROUP_CATEGORY_SELECTION_CHILD:
			
			try {
				String name = cate_src.getLast().getString(Integer.toString(idCode));
				listKategori.add((String) item.getTitle());
				kategori.setText(name);
			} catch (JSONException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
				api.listAttributes(new APIListener() {
					ProgressDialog pd;
					@Override
					public void onSuccess(Object res, Exception e,
							InternetTask task) {
						JSONObject src = (JSONObject) res;
						Iterator<String> iter = src.keys();
						while (iter.hasNext()) {
							try {
								final String key = iter.next();
								String field;
								String disp;
								field = src.getJSONObject(key).getString(
										"fieldName");
								disp = src.getJSONObject(key).getString(
										"displayName");
								attribs.put(field, disp);

								JSONArray values = src.getJSONObject(key)
										.getJSONArray("options");
								
								
								LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
								View view = inflater.inflate(R.layout.view_product_upload_field, null);
								TextView tx = (TextView) view.findViewById(R.id.field_text);
								tx.setText(disp);
								View vw = null;
								if (values.length() == 0) {
									EditText et = (EditText) view.findViewById(R.id.field_edittext);
									et.setVisibility(EditText.VISIBLE);
									vw = et;
								} else {

									final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
											getApplicationContext(),
											android.R.layout.simple_spinner_item);
									for (int ii = 0; ii < values.length(); ii++) {
										try {
											adapter.add(values.getString(ii));
										} catch (JSONException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
									adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
									Spinner spin = (Spinner) view.findViewById(R.id.field_spinner);
									spin.setAdapter(adapter);
									spin.setVisibility(Spinner.VISIBLE);
									vw = spin;
								}
								tx.setTextColor(Color.BLACK);
								len.addView(view);
								specs.put(field, vw);
							} catch (JSONException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						}

						len.requestLayout();
						pd.dismiss();
					}

					@Override
					public void onHold(InternetTask task) {
						// TODO Auto-generated method stub
						pd.dismiss();
					}

					@Override
					public void onExecute(InternetTask task) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onEnqueue(InternetTask task) {
						// TODO Auto-generated method stub
						pd = new ProgressDialog(context);

                        pd.setTitle("Isian Produk");

                        pd.setMessage("Tunggu sebentar, sedang membuat isian produk...");

                        pd.setCancelable(false);

                        pd.setIndeterminate(true);

                        pd.show();
					}
				}, "" + idCode);


			break;
		case GROUP_CATEGORY_BACK:
			cate_src.removeLast();
			listKategori.remove(listKategori.size()-1);
			closeContextMenu();
			openContextMenu(namaBarang);
			break;
		case GROUP_CATEGORY_CANCEL:
			finish();
			break;
		}
		return super.onContextItemSelected(item);
	}

	private JSONObject compileInfo() throws JSONException {
		JSONObject product = new JSONObject();
		product.put("category_id", category_id);
		product.put("name", namaBarang.getText().toString());
		product.put("price", hargaBarang.getText().toString());
		product.put("weight", beratBarang.getText().toString());
		product.put("stock", stokBarang.getText().toString());
		product.put("description_bb", deskripsiBarang.getText().toString());
		if (bekas.isChecked())
			product.put("new", "false");
		if (baru.isChecked())
			product.put("new", "true");
		product.put("negotiable", "true");
		JSONObject attrib_det = new JSONObject();
		for (String k : attribs.keySet()) {
			View v = specs.get(k);
			if (v instanceof EditText) {
				EditText e = (EditText) v;
				attrib_det.put(k, e.getText().toString());
			} else if (v instanceof Spinner) {
				Spinner s = (Spinner) v;
				attrib_det.put(k, s.getSelectedItem().toString());
			}

		}
		JSONObject result = new JSONObject();
		result.put("product", product);
		result.put("product_detail_attribute", attrib_det);
		return result;
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == PICK_FROM_SOURCE) {
			if (data != null) {
				Uri picUri = data.getData();
				performCrop(picUri);
			}
		} else if (requestCode == PICK_CROP) {
			if (data != null) {
				Bundle extras = data.getExtras();
				if (extras != null) {
					bitmap = extras.getParcelable("data");
					if (bitmap.getHeight() < 300 || bitmap.getWidth() < 300)
						Toast.makeText(getApplicationContext(),
								"Gambar kurang dari ukuran minimal",
								Toast.LENGTH_SHORT).show();
					else {
						try {
							final String s = "image";
							addImage(bitmap, s);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	private void performCrop(Uri uri) {
		try {
			// call the standard crop action intent (the user device may not
			// support it)
			Intent cropIntent = new Intent("com.android.camera.action.CROP");
			// indicate image type and Uri
			cropIntent.setDataAndType(uri, "image/*");
			// set crop properties
			cropIntent.putExtra("crop", "true");
			// indicate aspect of desired crop
			cropIntent.putExtra("aspectX", 1);
			cropIntent.putExtra("aspectY", 1);
			// indicate output X and Y
			cropIntent.putExtra("outputX", 300);
			cropIntent.putExtra("outputY", 300);
			// retrieve data on return
			cropIntent.putExtra("return-data", true);
			// start the activity - we handle returning in onActivityResult
			startActivityForResult(cropIntent, PICK_CROP);
		} catch (ActivityNotFoundException anfe) {
			// display an error message
			String errorMessage = "Whoops - your device doesn't support the crop action!";
			Toast toast = Toast
					.makeText(this, errorMessage, Toast.LENGTH_SHORT);
			toast.show();
		}
	}

	private void addImage(Bitmap b, String g) throws Exception {
		final String s = g;
		LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
		final View view = inflater.inflate(R.layout.view_product_upload_image, null);
		ImageView imgview = (ImageView) view.findViewById(R.id.thumb);
		imgview.setImageBitmap(b);
		final Button btn = (Button) view.findViewById(R.id.controlBtn);
//		final TextView imgname = (TextView) view.findViewById(R.id.imgname);
		final ProgressBar progress = (ProgressBar) view
				.findViewById(R.id.progress);
		final ImageView imgOk = (ImageView) view.findViewById(R.id.finishIcon);
		api.createImage(new APIListener() {

			@Override
			public void onSuccess(Object res, Exception e, InternetTask task) {
				// TODO Auto-generated method stub
				progress.setVisibility(ProgressBar.GONE);
				imgOk.setVisibility(ImageView.VISIBLE);
				String obj = (String) res;
				final String id = obj;
				img_ids.add(id);
				btn.setText("HAPUS");
				btn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						listImages.removeView(view);
						img_ids.remove(id);
					}
				});

			}

			@Override
			public void onHold(InternetTask task) {
				// TODO Auto-generated method stub
				imgOk.setImageResource(R.drawable.clear_button_image);
				imgOk.setVisibility(ImageView.VISIBLE);
				progress.setVisibility(ProgressBar.GONE);
			}

			@Override
			public void onExecute(InternetTask task) {
				// TODO Auto-generated method stub
				progress.setVisibility(ProgressBar.VISIBLE);
			}

			@Override
			public void onEnqueue(final InternetTask task) {
				// TODO Auto-generated method stub
				btn.setText("BATAL");
				btn.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						task.cancelProcess();
					}
				});
			}
		}, b);
		listImages.addView(view);
		listImages.requestLayout();
		listImages.invalidate();
	}
}
