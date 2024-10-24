using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Explosion : MonoBehaviour {
    public ParticleSystem explostionParticleSystem;
    private ParticleSystem.MainModule mainModule;
    void Start() {
        var main = explostionParticleSystem.GetComponent<ParticleSystem>().main;
        main.stopAction = ParticleSystemStopAction.Callback;
    }

    void Update() {

    }

    private void OnParticleSystemStopped() {
        Destroy(this.gameObject);

    }
}
