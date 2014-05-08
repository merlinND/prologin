///
// This file has been generated, if you wish to
// modify it in a permanent way, please refer
// to the script file : gen/generator_java.rb
//

#include "interface.hh"

// In case of errors...
template<typename Lang, typename Cxx>
Cxx lang2cxx(Lang in)
{
  return in.error_should_not_happens;
}

template<typename Cxx, typename Lang>
Lang cxx2lang(Cxx in)
{
  return in.error_should_not_happens;
}

template<typename Lang, typename Cxx>
std::vector<Cxx> lang2cxx_array(jobject in);

template<typename Cxx, typename Lang>
jarray cxx2lang_array(std::vector<Cxx> in);

// Basic type wrappers
template <>
jboolean cxx2lang<boolean, jboolean>(boolean in)
{
  return (jboolean)in;
}

template <>
boolean lang2cxx<jboolean, boolean>(jboolean in)
{
  return (boolean)in;
}

template <>
jarray cxx2lang_array<boolean, jboolean>(std::vector<boolean> in)
{
  jbooleanArray out = jrt.env->NewBooleanArray((jsize)in.size());
  jrt.env->SetBooleanArrayRegion(out, (jsize)0, (jsize)in.size(), (const jboolean*)in.data());
  return (jarray)out;
}

template <>
std::vector<boolean> lang2cxx_array<jboolean, boolean>(jobject in)
{
  jbooleanArray array = (jbooleanArray)in;
  jsize size = jrt.env->GetArrayLength(array);
  jboolean* datas = jrt.env->GetBooleanArrayElements(array, NULL);
  std::vector<boolean> out(datas, datas + size);
  jrt.env->ReleaseBooleanArrayElements(array, datas, JNI_ABORT);
  return out;
}

template <>
jbyte cxx2lang<byte, jbyte>(byte in)
{
  return (jbyte)in;
}

template <>
byte lang2cxx<jbyte, byte>(jbyte in)
{
  return (byte)in;
}

template <>
jarray cxx2lang_array<byte, jbyte>(std::vector<byte> in)
{
  jbyteArray out = jrt.env->NewByteArray((jsize)in.size());
  jrt.env->SetByteArrayRegion(out, (jsize)0, (jsize)in.size(), (const jbyte*)in.data());
  return (jarray)out;
}

template <>
std::vector<byte> lang2cxx_array<jbyte, byte>(jobject in)
{
  jbyteArray array = (jbyteArray)in;
  jsize size = jrt.env->GetArrayLength(array);
  jbyte* datas = jrt.env->GetByteArrayElements(array, NULL);
  std::vector<byte> out(datas, datas + size);
  jrt.env->ReleaseByteArrayElements(array, datas, JNI_ABORT);
  return out;
}

template <>
jchar cxx2lang<char, jchar>(char in)
{
  return (jchar)in;
}

template <>
char lang2cxx<jchar, char>(jchar in)
{
  return (char)in;
}

template <>
jarray cxx2lang_array<char, jchar>(std::vector<char> in)
{
  jcharArray out = jrt.env->NewCharArray((jsize)in.size());
  jrt.env->SetCharArrayRegion(out, (jsize)0, (jsize)in.size(), (const jchar*)in.data());
  return (jarray)out;
}

template <>
std::vector<char> lang2cxx_array<jchar, char>(jobject in)
{
  jcharArray array = (jcharArray)in;
  jsize size = jrt.env->GetArrayLength(array);
  jchar* datas = jrt.env->GetCharArrayElements(array, NULL);
  std::vector<char> out(datas, datas + size);
  jrt.env->ReleaseCharArrayElements(array, datas, JNI_ABORT);
  return out;
}

template <>
jshort cxx2lang<short, jshort>(short in)
{
  return (jshort)in;
}

template <>
short lang2cxx<jshort, short>(jshort in)
{
  return (short)in;
}

template <>
jarray cxx2lang_array<short, jshort>(std::vector<short> in)
{
  jshortArray out = jrt.env->NewShortArray((jsize)in.size());
  jrt.env->SetShortArrayRegion(out, (jsize)0, (jsize)in.size(), (const jshort*)in.data());
  return (jarray)out;
}

template <>
std::vector<short> lang2cxx_array<jshort, short>(jobject in)
{
  jshortArray array = (jshortArray)in;
  jsize size = jrt.env->GetArrayLength(array);
  jshort* datas = jrt.env->GetShortArrayElements(array, NULL);
  std::vector<short> out(datas, datas + size);
  jrt.env->ReleaseShortArrayElements(array, datas, JNI_ABORT);
  return out;
}

template <>
jint cxx2lang<int, jint>(int in)
{
  return (jint)in;
}

template <>
int lang2cxx<jint, int>(jint in)
{
  return (int)in;
}

template <>
jarray cxx2lang_array<int, jint>(std::vector<int> in)
{
  jintArray out = jrt.env->NewIntArray((jsize)in.size());
  jrt.env->SetIntArrayRegion(out, (jsize)0, (jsize)in.size(), (const jint*)in.data());
  return (jarray)out;
}

template <>
std::vector<int> lang2cxx_array<jint, int>(jobject in)
{
  jintArray array = (jintArray)in;
  jsize size = jrt.env->GetArrayLength(array);
  jint* datas = jrt.env->GetIntArrayElements(array, NULL);
  std::vector<int> out(datas, datas + size);
  jrt.env->ReleaseIntArrayElements(array, datas, JNI_ABORT);
  return out;
}

template <>
jlong cxx2lang<long, jlong>(long in)
{
  return (jlong)in;
}

template <>
long lang2cxx<jlong, long>(jlong in)
{
  return (long)in;
}

template <>
jarray cxx2lang_array<long, jlong>(std::vector<long> in)
{
  jlongArray out = jrt.env->NewLongArray((jsize)in.size());
  jrt.env->SetLongArrayRegion(out, (jsize)0, (jsize)in.size(), (const jlong*)in.data());
  return (jarray)out;
}

template <>
std::vector<long> lang2cxx_array<jlong, long>(jobject in)
{
  jlongArray array = (jlongArray)in;
  jsize size = jrt.env->GetArrayLength(array);
  jlong* datas = jrt.env->GetLongArrayElements(array, NULL);
  std::vector<long> out(datas, datas + size);
  jrt.env->ReleaseLongArrayElements(array, datas, JNI_ABORT);
  return out;
}

template <>
jfloat cxx2lang<float, jfloat>(float in)
{
  return (jfloat)in;
}

template <>
float lang2cxx<jfloat, float>(jfloat in)
{
  return (float)in;
}

template <>
jarray cxx2lang_array<float, jfloat>(std::vector<float> in)
{
  jfloatArray out = jrt.env->NewFloatArray((jsize)in.size());
  jrt.env->SetFloatArrayRegion(out, (jsize)0, (jsize)in.size(), (const jfloat*)in.data());
  return (jarray)out;
}

template <>
std::vector<float> lang2cxx_array<jfloat, float>(jobject in)
{
  jfloatArray array = (jfloatArray)in;
  jsize size = jrt.env->GetArrayLength(array);
  jfloat* datas = jrt.env->GetFloatArrayElements(array, NULL);
  std::vector<float> out(datas, datas + size);
  jrt.env->ReleaseFloatArrayElements(array, datas, JNI_ABORT);
  return out;
}

template <>
jdouble cxx2lang<double, jdouble>(double in)
{
  return (jdouble)in;
}

template <>
double lang2cxx<jdouble, double>(jdouble in)
{
  return (double)in;
}

template <>
jarray cxx2lang_array<double, jdouble>(std::vector<double> in)
{
  jdoubleArray out = jrt.env->NewDoubleArray((jsize)in.size());
  jrt.env->SetDoubleArrayRegion(out, (jsize)0, (jsize)in.size(), (const jdouble*)in.data());
  return (jarray)out;
}

template <>
std::vector<double> lang2cxx_array<jdouble, double>(jobject in)
{
  jdoubleArray array = (jdoubleArray)in;
  jsize size = jrt.env->GetArrayLength(array);
  jdouble* datas = jrt.env->GetDoubleArrayElements(array, NULL);
  std::vector<double> out(datas, datas + size);
  jrt.env->ReleaseDoubleArrayElements(array, datas, JNI_ABORT);
  return out;
}

// String wrappers
template <>
jstring cxx2lang<std::string, jstring>(std::string in)
{
  return jrt.env->NewStringUTF(in.data());
}

template <>
std::string lang2cxx<jstring, std::string>(jstring in)
{
  jboolean is_copy;
  const jchar* datas = jrt.env->GetStringChars(in, &is_copy);
  jsize size = jrt.env->GetStringLength(in);
  std::string out((const char*)datas, (size_t)size);
  if (is_copy)
    jrt.env->ReleaseStringChars(in, datas);
  return out;
}

// Object array wrappers (assume Lang::class exists)
template <typename Cxx, typename Lang>
jarray cxx2lang_array(std::vector<Cxx> in)
{
  jobjectArray out = jrt.env->NewObjectArray((jsize)in.size(), Lang::Class(), NULL);
  for (size_t i = 0; i < in.size(); i++)
    jrt.env->SetObjectArrayElement(out, (jsize)i, cxx2lang<Cxx, jobject>(in[i]));
  return out;
}

template <typename Lang, typename Cxx>
std::vector<Cxx> lang2cxx_array(jobject in)
{
  jobjectArray array = (jobjectArray)in;
  size_t size = (size_t)jrt.env->GetArrayLength(array);
  std::vector<Cxx> out;
  for (size_t i = 0; i < size; i++)
    out.push_back(lang2cxx<jobject, Cxx>(jrt.env->GetObjectArrayElement(array, (jsize)i)));
  return out;
}

///
// Information sur les cases
//
template<>
case_info lang2cxx<jobject, case_info>(jobject in)
{
  jmethodID ordinal = jrt.env->GetMethodID(CaseInfo::Class(), "ordinal", "()I");
  return case_info(lang2cxx<jint, int>(jrt.env->CallIntMethod(in, ordinal)));
}

template<>
jobject cxx2lang<case_info, jobject>(case_info in)
{
  jmethodID method = jrt.env->GetStaticMethodID(CaseInfo::Class(), "values", "()[LCaseInfo;");
  jobjectArray values = (jobjectArray)jrt.env->CallStaticObjectMethod(CaseInfo::Class(), method);
  return jrt.env->GetObjectArrayElement(values, (jsize)in);
}


///
// Erreurs possibles
//
template<>
erreur lang2cxx<jobject, erreur>(jobject in)
{
  jmethodID ordinal = jrt.env->GetMethodID(Erreur::Class(), "ordinal", "()I");
  return erreur(lang2cxx<jint, int>(jrt.env->CallIntMethod(in, ordinal)));
}

template<>
jobject cxx2lang<erreur, jobject>(erreur in)
{
  jmethodID method = jrt.env->GetStaticMethodID(Erreur::Class(), "values", "()[LErreur;");
  jobjectArray values = (jobjectArray)jrt.env->CallStaticObjectMethod(Erreur::Class(), method);
  return jrt.env->GetObjectArrayElement(values, (jsize)in);
}


///
// Représente la position sur la carte
//
template <>
position lang2cxx<jobject, position>(jobject in)
{
  position out;
  out.x = lang2cxx<jint, int>(jrt.env->GetIntField(in, jrt.env->GetFieldID(Position::Class(), "x", "I")));
  out.y = lang2cxx<jint, int>(jrt.env->GetIntField(in, jrt.env->GetFieldID(Position::Class(), "y", "I")));
  return out;
}

template <>
jobject cxx2lang<position, jobject>(position in)
{
  jobject out = jrt.env->NewObject(Position::Class(), jrt.env->GetMethodID(Position::Class(), "<init>", "()V"));
  jrt.env->SetIntField(out, jrt.env->GetFieldID(Position::Class(), "x", "I"), cxx2lang<int, jint>(in.x));
  jrt.env->SetIntField(out, jrt.env->GetFieldID(Position::Class(), "y", "I"), cxx2lang<int, jint>(in.y));
  return out;
}


///
// Représente une tourelle
//
template <>
tourelle lang2cxx<jobject, tourelle>(jobject in)
{
  tourelle out;
  out.pos = lang2cxx<jobject, position>(jrt.env->GetObjectField(in, jrt.env->GetFieldID(Tourelle::Class(), "pos", "LPosition;")));
  out.portee = lang2cxx<jint, int>(jrt.env->GetIntField(in, jrt.env->GetFieldID(Tourelle::Class(), "portee", "I")));
  out.joueur = lang2cxx<jint, int>(jrt.env->GetIntField(in, jrt.env->GetFieldID(Tourelle::Class(), "joueur", "I")));
  out.vie = lang2cxx<jint, int>(jrt.env->GetIntField(in, jrt.env->GetFieldID(Tourelle::Class(), "vie", "I")));
  out.attaque = lang2cxx<jint, int>(jrt.env->GetIntField(in, jrt.env->GetFieldID(Tourelle::Class(), "attaque", "I")));
  return out;
}

template <>
jobject cxx2lang<tourelle, jobject>(tourelle in)
{
  jobject out = jrt.env->NewObject(Tourelle::Class(), jrt.env->GetMethodID(Tourelle::Class(), "<init>", "()V"));
  jrt.env->SetObjectField(out, jrt.env->GetFieldID(Tourelle::Class(), "pos", "LPosition;"), cxx2lang<position, jobject>(in.pos));
  jrt.env->SetIntField(out, jrt.env->GetFieldID(Tourelle::Class(), "portee", "I"), cxx2lang<int, jint>(in.portee));
  jrt.env->SetIntField(out, jrt.env->GetFieldID(Tourelle::Class(), "joueur", "I"), cxx2lang<int, jint>(in.joueur));
  jrt.env->SetIntField(out, jrt.env->GetFieldID(Tourelle::Class(), "vie", "I"), cxx2lang<int, jint>(in.vie));
  jrt.env->SetIntField(out, jrt.env->GetFieldID(Tourelle::Class(), "attaque", "I"), cxx2lang<int, jint>(in.attaque));
  return out;
}


///
// Retourne le type de la case à l'emplacement `pos`
//
jobject info_case(JNIEnv* _env, jobject _obj, jobject pos)
{
  return cxx2lang<case_info, jobject>(api_info_case(lang2cxx<jobject, position>(pos)));
}


///
// Retourne la liste des tourelles qui appartiennent au joueur ``joueur``
//
jarray tourelles_joueur(JNIEnv* _env, jobject _obj, jint joueur)
{
  return cxx2lang_array<tourelle, Tourelle>(api_tourelles_joueur(lang2cxx<jint, int>(joueur)));
}


///
// Retourne la magie que possède le joueur ``joueur``
//
jint magie(JNIEnv* _env, jobject _obj, jint joueur)
{
  return cxx2lang<int, jint>(api_magie(lang2cxx<jint, int>(joueur)));
}


///
// Retourne le nombre de sorciers du joueur ``joueur`` sur la case ``pos``
//
jint nb_sorciers(JNIEnv* _env, jobject _obj, jobject pos, jint joueur)
{
  return cxx2lang<int, jint>(api_nb_sorciers(lang2cxx<jobject, position>(pos), lang2cxx<jint, int>(joueur)));
}


///
// Retourne le nombre de sorciers du joueur ``joueur`` déplacables sur la case ``pos``
//
jint nb_sorciers_deplacables(JNIEnv* _env, jobject _obj, jobject pos, jint joueur)
{
  return cxx2lang<int, jint>(api_nb_sorciers_deplacables(lang2cxx<jobject, position>(pos), lang2cxx<jint, int>(joueur)));
}


///
// Retourne le numéro du joueur qui contrôle la case ``pos``
//
jint joueur_case(JNIEnv* _env, jobject _obj, jobject pos)
{
  return cxx2lang<int, jint>(api_joueur_case(lang2cxx<jobject, position>(pos)));
}


///
// Retourne la tourelle située sur la case ``pos``
//
jobject tourelle_case(JNIEnv* _env, jobject _obj, jobject pos)
{
  return cxx2lang<tourelle, jobject>(api_tourelle_case(lang2cxx<jobject, position>(pos)));
}


///
// Retourne la position de la base du joueur ``joueur``
//
jobject base_joueur(JNIEnv* _env, jobject _obj, jint joueur)
{
  return cxx2lang<position, jobject>(api_base_joueur(lang2cxx<jint, int>(joueur)));
}


///
// Retourne vrai si l'on peut construire sur la case ``pos``
//
jboolean constructible(JNIEnv* _env, jobject _obj, jobject pos, jint joueur)
{
  return cxx2lang<boolean, jboolean>(api_constructible(lang2cxx<jobject, position>(pos), lang2cxx<jint, int>(joueur)));
}


///
// Retourne la liste des positions constituant le plus court chemin allant de la case ``pos1`` à la case ``pos2``. Attention : Cette fonction est lente.
//
jarray chemin(JNIEnv* _env, jobject _obj, jobject pos1, jobject pos2)
{
  return cxx2lang_array<position, Position>(api_chemin(lang2cxx<jobject, position>(pos1), lang2cxx<jobject, position>(pos2)));
}


///
// Construire une tourelle à la position ``pos``
//
jobject construire(JNIEnv* _env, jobject _obj, jobject pos, jint portee)
{
  return cxx2lang<erreur, jobject>(api_construire(lang2cxx<jobject, position>(pos), lang2cxx<jint, int>(portee)));
}


///
// Supprimer une tourelle à la position ``pos``
//
jobject supprimer(JNIEnv* _env, jobject _obj, jobject pos)
{
  return cxx2lang<erreur, jobject>(api_supprimer(lang2cxx<jobject, position>(pos)));
}


///
// Tirer avec ``pts`` points de dégats depuis la tourelles ``tourelle`` sur la position ``cible``
//
jobject tirer(JNIEnv* _env, jobject _obj, jint pts, jobject tourelle, jobject cible)
{
  return cxx2lang<erreur, jobject>(api_tirer(lang2cxx<jint, int>(pts), lang2cxx<jobject, position>(tourelle), lang2cxx<jobject, position>(cible)));
}


///
// Créer ``nb`` sorciers dans la base
//
jobject creer(JNIEnv* _env, jobject _obj, jint nb)
{
  return cxx2lang<erreur, jobject>(api_creer(lang2cxx<jint, int>(nb)));
}


///
// Déplace ``nb`` sorciers de la position ``depart`` jusqu'à la position ``arrivee``.
//
jobject deplacer(JNIEnv* _env, jobject _obj, jobject depart, jobject arrivee, jint nb)
{
  return cxx2lang<erreur, jobject>(api_deplacer(lang2cxx<jobject, position>(depart), lang2cxx<jobject, position>(arrivee), lang2cxx<jint, int>(nb)));
}


///
// Attaquer la tourelle à la position ``cible`` depuis la position ``pos``
//
jobject assieger(JNIEnv* _env, jobject _obj, jobject pos, jobject cible, jint nb_sorciers)
{
  return cxx2lang<erreur, jobject>(api_assieger(lang2cxx<jobject, position>(pos), lang2cxx<jobject, position>(cible), lang2cxx<jint, int>(nb_sorciers)));
}


///
// Retourne le numéro de votre joueur
//
jint moi(JNIEnv* _env, jobject _obj)
{
  return cxx2lang<int, jint>(api_moi());
}


///
// Retourne la liste des numéros de vos adversaires
//
jarray adversaires(JNIEnv* _env, jobject _obj)
{
  return cxx2lang_array<int, jint>(api_adversaires());
}


///
// Retourne le numéro du tour actuel
//
jint tour_actuel(JNIEnv* _env, jobject _obj)
{
  return cxx2lang<int, jint>(api_tour_actuel());
}


///
// Retourne la distance entre deux positions
//
jint distance(JNIEnv* _env, jobject _obj, jobject depart, jobject arrivee)
{
  return cxx2lang<int, jint>(api_distance(lang2cxx<jobject, position>(depart), lang2cxx<jobject, position>(arrivee)));
}


///
// Annule la dernière action
//
jobject annuler(JNIEnv* _env, jobject _obj)
{
  return cxx2lang<erreur, jobject>(api_annuler());
}


///
// Affiche le contenu d'une valeur de type case_info
//
void afficher_case_info(JNIEnv* _env, jobject _obj, jobject v)
{
  api_afficher_case_info(lang2cxx<jobject, case_info>(v));
}


///
// Affiche le contenu d'une valeur de type erreur
//
void afficher_erreur(JNIEnv* _env, jobject _obj, jobject v)
{
  api_afficher_erreur(lang2cxx<jobject, erreur>(v));
}


///
// Affiche le contenu d'une valeur de type position
//
void afficher_position(JNIEnv* _env, jobject _obj, jobject v)
{
  api_afficher_position(lang2cxx<jobject, position>(v));
}


///
// Affiche le contenu d'une valeur de type tourelle
//
void afficher_tourelle(JNIEnv* _env, jobject _obj, jobject v)
{
  api_afficher_tourelle(lang2cxx<jobject, tourelle>(v));
}


///
// Fonction appelée au début de la partie
//
extern "C" void partie_debut()
{
  bool attached = jrt.function_enter();
  jmethodID method = jrt.env->GetMethodID(Prologin::Class(), "partie_debut", "()V");
  jrt.env->CallVoidMethod(jrt.prologin, method);
  if (jrt.env->ExceptionOccurred())
  {
    jrt.env->ExceptionDescribe();
    exit(1);
  }
  jrt.function_exit(attached);
}


///
// Fonction appelée pendant la phase de construction
//
extern "C" void phase_construction()
{
  bool attached = jrt.function_enter();
  jmethodID method = jrt.env->GetMethodID(Prologin::Class(), "phase_construction", "()V");
  jrt.env->CallVoidMethod(jrt.prologin, method);
  if (jrt.env->ExceptionOccurred())
  {
    jrt.env->ExceptionDescribe();
    exit(1);
  }
  jrt.function_exit(attached);
}


///
// Fonction appelée pendant la phase de déplacement
//
extern "C" void phase_deplacement()
{
  bool attached = jrt.function_enter();
  jmethodID method = jrt.env->GetMethodID(Prologin::Class(), "phase_deplacement", "()V");
  jrt.env->CallVoidMethod(jrt.prologin, method);
  if (jrt.env->ExceptionOccurred())
  {
    jrt.env->ExceptionDescribe();
    exit(1);
  }
  jrt.function_exit(attached);
}


///
// Fonction appelée pendant la phase de tirs des tourelles
//
extern "C" void phase_tirs()
{
  bool attached = jrt.function_enter();
  jmethodID method = jrt.env->GetMethodID(Prologin::Class(), "phase_tirs", "()V");
  jrt.env->CallVoidMethod(jrt.prologin, method);
  if (jrt.env->ExceptionOccurred())
  {
    jrt.env->ExceptionDescribe();
    exit(1);
  }
  jrt.function_exit(attached);
}


///
// Fonction appelée pendant la phase de siège des tourelles
//
extern "C" void phase_siege()
{
  bool attached = jrt.function_enter();
  jmethodID method = jrt.env->GetMethodID(Prologin::Class(), "phase_siege", "()V");
  jrt.env->CallVoidMethod(jrt.prologin, method);
  if (jrt.env->ExceptionOccurred())
  {
    jrt.env->ExceptionDescribe();
    exit(1);
  }
  jrt.function_exit(attached);
}


///
// Fonction appelée à la fin de la partie
//
extern "C" void partie_fin()
{
  bool attached = jrt.function_enter();
  jmethodID method = jrt.env->GetMethodID(Prologin::Class(), "partie_fin", "()V");
  jrt.env->CallVoidMethod(jrt.prologin, method);
  if (jrt.env->ExceptionOccurred())
  {
    jrt.env->ExceptionDescribe();
    exit(1);
  }
  jrt.function_exit(attached);
}


jclass Prologin::Class()
{
  return jrt.env->FindClass("Prologin");
}

jclass CaseInfo::Class()
{
  return jrt.env->FindClass("CaseInfo");
}

jclass Erreur::Class()
{
  return jrt.env->FindClass("Erreur");
}

jclass Position::Class()
{
  return jrt.env->FindClass("Position");
}

jclass Tourelle::Class()
{
  return jrt.env->FindClass("Tourelle");
}

static void _register_native_methods(JNIEnv* env)
{
  JNINativeMethod methods[] = {
    {(char*)"info_case", (char*)"(LPosition;)LCaseInfo;", (void*)&info_case},
    {(char*)"tourelles_joueur", (char*)"(I)[LTourelle;", (void*)&tourelles_joueur},
    {(char*)"magie", (char*)"(I)I", (void*)&magie},
    {(char*)"nb_sorciers", (char*)"(LPosition;I)I", (void*)&nb_sorciers},
    {(char*)"nb_sorciers_deplacables", (char*)"(LPosition;I)I", (void*)&nb_sorciers_deplacables},
    {(char*)"joueur_case", (char*)"(LPosition;)I", (void*)&joueur_case},
    {(char*)"tourelle_case", (char*)"(LPosition;)LTourelle;", (void*)&tourelle_case},
    {(char*)"base_joueur", (char*)"(I)LPosition;", (void*)&base_joueur},
    {(char*)"constructible", (char*)"(LPosition;I)Z", (void*)&constructible},
    {(char*)"chemin", (char*)"(LPosition;LPosition;)[LPosition;", (void*)&chemin},
    {(char*)"construire", (char*)"(LPosition;I)LErreur;", (void*)&construire},
    {(char*)"supprimer", (char*)"(LPosition;)LErreur;", (void*)&supprimer},
    {(char*)"tirer", (char*)"(ILPosition;LPosition;)LErreur;", (void*)&tirer},
    {(char*)"creer", (char*)"(I)LErreur;", (void*)&creer},
    {(char*)"deplacer", (char*)"(LPosition;LPosition;I)LErreur;", (void*)&deplacer},
    {(char*)"assieger", (char*)"(LPosition;LPosition;I)LErreur;", (void*)&assieger},
    {(char*)"moi", (char*)"()I", (void*)&moi},
    {(char*)"adversaires", (char*)"()[I", (void*)&adversaires},
    {(char*)"tour_actuel", (char*)"()I", (void*)&tour_actuel},
    {(char*)"distance", (char*)"(LPosition;LPosition;)I", (void*)&distance},
    {(char*)"annuler", (char*)"()LErreur;", (void*)&annuler},
    {(char*)"afficher_case_info", (char*)"(LCaseInfo;)V", (void*)&afficher_case_info},
    {(char*)"afficher_erreur", (char*)"(LErreur;)V", (void*)&afficher_erreur},
    {(char*)"afficher_position", (char*)"(LPosition;)V", (void*)&afficher_position},
    {(char*)"afficher_tourelle", (char*)"(LTourelle;)V", (void*)&afficher_tourelle}
  };
  env->RegisterNatives(Prologin::Class(), methods, sizeof(methods)/sizeof(methods[0]));
}

ProloginJavaRunTime jrt;

ProloginJavaRunTime::ProloginJavaRunTime()
{
 std::string classpath = "-Djava.class.path=";
  char* champion_path = getenv("CHAMPION_PATH");
  if (champion_path == NULL)
    champion_path = (char*)"./";
  classpath.append(champion_path);

  JavaVMInitArgs vm_args; /* JDK/JRE 6 VM initialization arguments */
  JavaVMOption options[2];
  options[0].optionString = (char*)classpath.c_str();
  options[1].optionString = (char*)"-ea";
  vm_args.version = JNI_VERSION_1_6;
  vm_args.nOptions = 2;
  vm_args.options = options;
  vm_args.ignoreUnrecognized = false;
  JNI_CreateJavaVM(&jvm, (void**)&env, &vm_args);
  prologin = env->NewObject(Prologin::Class(), env->GetMethodID(Prologin::Class(), "<init>", "()V"));
  _register_native_methods(env);
}

ProloginJavaRunTime::~ProloginJavaRunTime()
{
  jvm->DestroyJavaVM();
}

bool ProloginJavaRunTime::function_enter()
{
  if (jvm->GetEnv((void**)&env, JNI_VERSION_1_6) == JNI_OK)
    return false;
  jvm->AttachCurrentThread((void**)&env, NULL);
  return true;
}

void ProloginJavaRunTime::function_exit(bool attached)
{
  if (attached)
    jvm->DetachCurrentThread();
}

