/* 
 * Copyright 2019 José A. Pacheco Ondoño - joanpaon@gmail.com.
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
package org.japo.java.forms;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.japo.java.components.BackgroundPanel;
import org.japo.java.libraries.UtilesSwing;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public final class GUI extends JFrame {

    // Propiedades App
    public static final String PRP_FAVICON_RESOURCE = "favicon_resource";
    public static final String PRP_FONT_RESOURCE = "font_resource";
    public static final String PRP_FORM_HEIGHT = "form_height";
    public static final String PRP_FORM_WIDTH = "form_width";
    public static final String PRP_FORM_TITLE = "form_title";
    public static final String PRP_IMAGE_RESOURCE = "image_resource";
    public static final String PRP_LOOK_AND_FEEL_PROFILE = "look_and_feel_profile";

    // Valores por Defecto
    public static final String DEF_FAVICON_RESOURCE = "img/favicon.png";
    public static final String DEF_FONT_FALLBACK_NAME = Font.SERIF;
    public static final String DEF_FONT_SYSTEM_NAME = "Kaufmann BT";
    public static final int DEF_FORM_HEIGHT = 300;
    public static final int DEF_FORM_WIDTH = 500;
    public static final String DEF_FORM_TITLE = "Swing Manual App";
    public static final String DEF_LOOK_AND_FEEL_PROFILE = UtilesSwing.LNF_WINDOWS_PROFILE;

    // Referencias
    private final Properties prp;

    // Componentes
    private JLabel lblSample;
    private JPanel pnlPpal;

    // Fuentes
    private Font fntSample;

    // Imágenes
    private Image imgSample;

    // Constructor
    public GUI(Properties prp) {
        // Conectar Referencia
        this.prp = prp;

        // Inicialización Anterior
        initBefore();

        // Creación Interfaz
        initComponents();

        // Inicializacion Posterior
        initAfter();
    }

    // Construcción - GUI
    private void initComponents() {
        // Fuentes
        fntSample = UtilesSwing.generarFuenteRecurso(
                prp.getProperty(PRP_FONT_RESOURCE),
                DEF_FONT_SYSTEM_NAME,
                DEF_FONT_FALLBACK_NAME);

        // Imágenes
        imgSample = UtilesSwing.importarImagenRecurso(
                prp.getProperty(PRP_IMAGE_RESOURCE));

        // Etiqueta Muestra
        lblSample = new JLabel("Connect the dots!!!");
        lblSample.setFont(fntSample.deriveFont(Font.BOLD, 60));
        lblSample.setHorizontalAlignment(JLabel.RIGHT);

        // Panel Principal
        pnlPpal = new BackgroundPanel(imgSample);
        pnlPpal.setLayout(new GridBagLayout());
        pnlPpal.add(lblSample);

        // Ventana Principal
        setContentPane(pnlPpal);
        setTitle(prp.getProperty(PRP_FORM_TITLE, DEF_FORM_TITLE));
        try {
            int height = Integer.parseInt(prp.getProperty(PRP_FORM_HEIGHT));
            int width = Integer.parseInt(prp.getProperty(PRP_FORM_WIDTH));
            setSize(width, height);
        } catch (NumberFormatException e) {
            setSize(DEF_FORM_WIDTH, DEF_FORM_HEIGHT);
        }
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Inicialización Anterior    
    private void initBefore() {
        // Establecer LnF
        UtilesSwing.establecerLnFProfile(prp.getProperty(
                PRP_LOOK_AND_FEEL_PROFILE, DEF_LOOK_AND_FEEL_PROFILE));
    }

    // Inicialización Posterior
    private void initAfter() {
        // Establecer Favicon
        UtilesSwing.establecerFavicon(this, prp.getProperty(
                PRP_FAVICON_RESOURCE, DEF_FAVICON_RESOURCE));
    }
}
