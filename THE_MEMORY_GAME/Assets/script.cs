using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
using System;
using System.Linq;

public class script : MonoBehaviour
{
    public GameObject panelvictoria;
    public GameObject panelmain;
    public GameObject panelgame;
    public GameObject panelcreditos;
    public GameObject panelnombres;
    public Text player1;
    public Text player2;
    public Text jugador1;
    public Text jugador2;
    public Text notif;
    public Text tiempo;
    public Text error;
    public Text textoWin;
    public Image[] array;
    public Image cartaActual=null;
    public Image cartaSelected=null;
    public GameObject[] vidas1;
    public GameObject[] vidas2;
    public Vector2[] posiciones;
    public double respiro;
    public double reloj=10;
    public int jugadoractual;
    public int puntuaciontotal = 0;
    public int puntos1=0;
    public int puntos1actuales=0;
    public int puntos2=0;
    public int puntos2actuales=0;
    public int vidasrestantes1=4;
    public int vidasrestantes2=4;
    public AudioSource correcto;
    public AudioSource incorrecto;
    public AudioSource ambiente;
    public AudioSource sintiempo;
    public AudioSource success;
    
    // Start is called before the first frame update
    void Start()
    {
        ambiente.Play();
        System.Random rnd = new System.Random();
        jugadoractual=UnityEngine.Random.Range(1, 3);
        array = array.OrderBy(x => rnd.Next()).ToArray();
        panelmain.SetActive(true);
        panelcreditos.SetActive(false);
        panelvictoria.SetActive(false);
        panelgame.SetActive(false);
        panelnombres.SetActive(false);
        foreach (Image recorrer in array) {
            recorrer.GetComponent<RectTransform>().anchoredPosition = (posiciones[Array.IndexOf(array, recorrer)]);
        }
    }

    // Update is called once per frame
    void Update()
    {
        
        if (panelgame.activeSelf) {
            if (puntuaciontotal < 10) {
                if (jugadoractual == 1) {
                    notif.gameObject.SetActive(true);
                    puntos2actuales = puntos2;
                    jugador2.color = Color.white;
                    jugador2.fontStyle = FontStyle.Normal;
                    jugador1.color = Color.black;
                    jugador1.fontStyle = FontStyle.Bold;
                    notif.text = "TURNO JUGADOR 1";
                    respiro += Time.deltaTime;
                    if (respiro >= 2) {
                        notif.gameObject.SetActive(false);
                        reloj -= Time.deltaTime;
                        int mostrar = (int)reloj;
                        tiempo.text = mostrar.ToString();
                        if (reloj <= 0 && puntos1==puntos1actuales) {
                            incorrecto.Play();
                            vidas1[vidasrestantes1].SetActive(false);
                            vidasrestantes1--;
                            if (vidasrestantes1 < 0) {
                                notif.gameObject.SetActive(false);
                                Invoke("mostrarvictoria", 1f);
                                textoWin.text = "¡Gana el jugador 2!";
                            }
                            else {
                                jugadoractual = 1;
                                reloj = 10;
                                respiro = 0;
                            }
                        }
                        else if (puntos1 != puntos1actuales) {
                            jugadoractual = 2;
                            reloj = 10;
                            respiro = 0;
                        }
                    }
                }
                else {
                    notif.gameObject.SetActive(true);
                    puntos1actuales = puntos1;
                    jugador1.color = Color.white;
                    jugador1.fontStyle = FontStyle.Normal;
                    jugador2.color = Color.black;
                    jugador2.fontStyle = FontStyle.Bold;
                    notif.text = "TURNO JUGADOR 2";
                    respiro += Time.deltaTime;
                    if (respiro >= 2) {
                        notif.gameObject.SetActive(false);
                        reloj -= Time.deltaTime;
                        int mostrar = (int)reloj;
                        tiempo.text = mostrar.ToString();
                        if (reloj <= 3) {
                            sintiempo.Play();
                        }
                        if (reloj <= 0 && puntos2==puntos2actuales) {
                            incorrecto.Play();
                            vidas2[vidasrestantes2].SetActive(false);
                            vidasrestantes2--;
                            if (vidasrestantes2 < 0) {
                                notif.gameObject.SetActive(false);
                                Invoke("mostrarvictoria", 1f);
                                textoWin.text = "¡Gana el jugador 1!";
                            }
                            else {
                                jugadoractual = 1;
                                reloj = 10;
                                respiro = 0;
                            }
                        }
                        else if (puntos2 != puntos2actuales) {
                            jugadoractual = 1;
                            reloj = 10;
                            respiro = 0;
                        }
                    }
                }
            }
            else {
                Invoke("mostrarwin", 1f);
                if (puntos1>puntos2) {
                    textoWin.text = "¡Gana el jugador 1!";
                }
                else {
                    textoWin.text = "¡Gana el jugador 2!";
                }
            }
        }
    }

    public void reiniciar() {
        SceneManager.LoadScene(0);
    }
    public void cerrar() {
        Application.Quit();
    } 
    public void mostrargame() {
        if (player1.text != "" && player2.text != "") {
            jugador1.text = player1.text;
            jugador2.text = player2.text;
            panelnombres.SetActive(false);
            panelgame.SetActive(true);
        } else {
            error.text = "Introduzcan sus nombres";
        }
    }
    public void mostrarnombres() {
        panelmain.SetActive(false);
        panelnombres.SetActive(true);
    }
    public void mostrarcreditos() {
        panelmain.SetActive(false);
        panelcreditos.SetActive(true);
    }
    public void mostrarvictoria() {
        ambiente.Stop();
        success.Play();
        panelgame.SetActive(false);
        panelvictoria.SetActive(true);
    }
    public void mostrarmain() {
        panelcreditos.SetActive(false);
        panelgame.SetActive(false);
        panelmain.SetActive(true);
        respiro = 0;
        reloj = 10;
    }
    public void carta() {
        Transform carta = UnityEngine.EventSystems.EventSystem.current.currentSelectedGameObject.transform.parent;
        if (cartaActual == null) {
            carta.GetChild(1).gameObject.SetActive(true);
            cartaActual = carta.GetChild(1).GetComponent<Image>();
        }
        else {
            cartaSelected = carta.GetChild(1).GetComponent<Image>();
            cartaSelected.gameObject.SetActive(true);
            if (cartaActual.sprite == cartaSelected.sprite) {
                correcto.Play();
                if (jugadoractual == 1) {
                    puntos1 ++;
                }
                else {
                    puntos2 ++;
                }
                puntuaciontotal++;
                cartaActual = null;
                cartaSelected = null;
            }
            else {
                incorrecto.Play();
                Invoke("ocultarcartas", 0.5f);
                if (jugadoractual==1) {
                    vidas1[vidasrestantes1].SetActive(false);
                    vidasrestantes1--;
                    if (vidasrestantes1 < 0) {
                        notif.gameObject.SetActive(false);
                        Invoke("mostrarvictoria", 1f);
                        textoWin.text = "¡Gana el jugador 2!";
                    }
                    else {
                        jugadoractual = 2;
                        reloj = 10;
                        respiro = 0;
                    }
                }
                else {
                    vidas2[vidasrestantes2].SetActive(false);
                    vidasrestantes2--;
                    if (vidasrestantes2 < 0) {
                        notif.gameObject.SetActive(false);
                        Invoke("mostrarvictoria", 1f);
                        textoWin.text = "¡Gana el jugador 1!";
                    }
                    else {
                        jugadoractual = 1;
                        reloj = 10;
                        respiro = 0;
                    }
                }
            }
        }
    }
    public void ocultarcartas() {
        cartaActual.gameObject.SetActive(false);
        cartaSelected.gameObject.SetActive(false);
        cartaActual = null;
        cartaSelected = null;
    }
}