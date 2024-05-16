
namespace BrainyBattleAdmin
{
    partial class BrainyBattleAdmin
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.buttonConectare = new System.Windows.Forms.Button();
            this.textBoxUsername = new System.Windows.Forms.TextBox();
            this.textBoxParola = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.groupBoxUser = new System.Windows.Forms.GroupBox();
            this.buttonDeleteUser = new System.Windows.Forms.Button();
            this.buttonViewUser = new System.Windows.Forms.Button();
            this.listBox2 = new System.Windows.Forms.ListBox();
            this.groupBoxQuiz = new System.Windows.Forms.GroupBox();
            this.groupBoxChat = new System.Windows.Forms.GroupBox();
            this.listBox1 = new System.Windows.Forms.ListBox();
            this.buttonDelMes = new System.Windows.Forms.Button();
            this.button1 = new System.Windows.Forms.Button();
            this.buttonAddQuestion = new System.Windows.Forms.Button();
            this.textBoxQuest = new System.Windows.Forms.TextBox();
            this.textBoxAnsCor = new System.Windows.Forms.TextBox();
            this.textBoxScor = new System.Windows.Forms.TextBox();
            this.textBoxAns1 = new System.Windows.Forms.TextBox();
            this.textBoxAns2 = new System.Windows.Forms.TextBox();
            this.textBoxAns3 = new System.Windows.Forms.TextBox();
            this.textBoxAns4 = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.labelQuest = new System.Windows.Forms.Label();
            this.labelEpoca = new System.Windows.Forms.Label();
            this.label10 = new System.Windows.Forms.Label();
            this.comboBoxEpoca = new System.Windows.Forms.ComboBox();
            this.buttonRemoveContent = new System.Windows.Forms.Button();
            this.groupBoxUser.SuspendLayout();
            this.groupBoxQuiz.SuspendLayout();
            this.groupBoxChat.SuspendLayout();
            this.SuspendLayout();
            // 
            // buttonConectare
            // 
            this.buttonConectare.Location = new System.Drawing.Point(76, 108);
            this.buttonConectare.Name = "buttonConectare";
            this.buttonConectare.Size = new System.Drawing.Size(87, 23);
            this.buttonConectare.TabIndex = 0;
            this.buttonConectare.Text = "Conectare";
            this.buttonConectare.UseVisualStyleBackColor = true;
            this.buttonConectare.Click += new System.EventHandler(this.buttonConectare_ClickAsync);
            // 
            // textBoxUsername
            // 
            this.textBoxUsername.Location = new System.Drawing.Point(76, 34);
            this.textBoxUsername.Name = "textBoxUsername";
            this.textBoxUsername.Size = new System.Drawing.Size(100, 20);
            this.textBoxUsername.TabIndex = 1;
            this.textBoxUsername.Text = "admin";
            // 
            // textBoxParola
            // 
            this.textBoxParola.Location = new System.Drawing.Point(76, 61);
            this.textBoxParola.Name = "textBoxParola";
            this.textBoxParola.PasswordChar = '*';
            this.textBoxParola.Size = new System.Drawing.Size(100, 20);
            this.textBoxParola.TabIndex = 2;
            this.textBoxParola.Text = "ParolaComplicata";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(13, 34);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(58, 13);
            this.label1.TabIndex = 3;
            this.label1.Text = "Username:";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(16, 61);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(40, 13);
            this.label2.TabIndex = 4;
            this.label2.Text = "Parola:";
            // 
            // groupBoxUser
            // 
            this.groupBoxUser.Controls.Add(this.buttonDeleteUser);
            this.groupBoxUser.Controls.Add(this.buttonViewUser);
            this.groupBoxUser.Controls.Add(this.listBox2);
            this.groupBoxUser.Enabled = false;
            this.groupBoxUser.Location = new System.Drawing.Point(371, 228);
            this.groupBoxUser.Name = "groupBoxUser";
            this.groupBoxUser.Size = new System.Drawing.Size(412, 205);
            this.groupBoxUser.TabIndex = 5;
            this.groupBoxUser.TabStop = false;
            this.groupBoxUser.Text = "User";
            // 
            // buttonDeleteUser
            // 
            this.buttonDeleteUser.Enabled = false;
            this.buttonDeleteUser.Location = new System.Drawing.Point(288, 167);
            this.buttonDeleteUser.Name = "buttonDeleteUser";
            this.buttonDeleteUser.Size = new System.Drawing.Size(118, 23);
            this.buttonDeleteUser.TabIndex = 3;
            this.buttonDeleteUser.Text = "Ștergere user selectat";
            this.buttonDeleteUser.UseVisualStyleBackColor = true;
            this.buttonDeleteUser.Click += new System.EventHandler(this.buttonDeleteUser_Click);
            // 
            // buttonViewUser
            // 
            this.buttonViewUser.Location = new System.Drawing.Point(7, 167);
            this.buttonViewUser.Name = "buttonViewUser";
            this.buttonViewUser.Size = new System.Drawing.Size(115, 23);
            this.buttonViewUser.TabIndex = 2;
            this.buttonViewUser.Text = "Vizualizare utilizatori";
            this.buttonViewUser.UseVisualStyleBackColor = true;
            this.buttonViewUser.Click += new System.EventHandler(this.button3_Click);
            // 
            // listBox2
            // 
            this.listBox2.FormattingEnabled = true;
            this.listBox2.Location = new System.Drawing.Point(7, 19);
            this.listBox2.Name = "listBox2";
            this.listBox2.Size = new System.Drawing.Size(405, 121);
            this.listBox2.TabIndex = 1;
            // 
            // groupBoxQuiz
            // 
            this.groupBoxQuiz.Controls.Add(this.buttonRemoveContent);
            this.groupBoxQuiz.Controls.Add(this.comboBoxEpoca);
            this.groupBoxQuiz.Controls.Add(this.label10);
            this.groupBoxQuiz.Controls.Add(this.labelEpoca);
            this.groupBoxQuiz.Controls.Add(this.labelQuest);
            this.groupBoxQuiz.Controls.Add(this.label7);
            this.groupBoxQuiz.Controls.Add(this.label6);
            this.groupBoxQuiz.Controls.Add(this.label5);
            this.groupBoxQuiz.Controls.Add(this.label4);
            this.groupBoxQuiz.Controls.Add(this.label3);
            this.groupBoxQuiz.Controls.Add(this.textBoxAns4);
            this.groupBoxQuiz.Controls.Add(this.textBoxAns3);
            this.groupBoxQuiz.Controls.Add(this.textBoxAns2);
            this.groupBoxQuiz.Controls.Add(this.textBoxAns1);
            this.groupBoxQuiz.Controls.Add(this.textBoxScor);
            this.groupBoxQuiz.Controls.Add(this.textBoxAnsCor);
            this.groupBoxQuiz.Controls.Add(this.textBoxQuest);
            this.groupBoxQuiz.Controls.Add(this.buttonAddQuestion);
            this.groupBoxQuiz.Enabled = false;
            this.groupBoxQuiz.Location = new System.Drawing.Point(288, 12);
            this.groupBoxQuiz.Name = "groupBoxQuiz";
            this.groupBoxQuiz.Size = new System.Drawing.Size(495, 210);
            this.groupBoxQuiz.TabIndex = 7;
            this.groupBoxQuiz.TabStop = false;
            this.groupBoxQuiz.Text = "Quiz";
            // 
            // groupBoxChat
            // 
            this.groupBoxChat.Controls.Add(this.listBox1);
            this.groupBoxChat.Controls.Add(this.buttonDelMes);
            this.groupBoxChat.Controls.Add(this.button1);
            this.groupBoxChat.Enabled = false;
            this.groupBoxChat.Location = new System.Drawing.Point(16, 228);
            this.groupBoxChat.Name = "groupBoxChat";
            this.groupBoxChat.Size = new System.Drawing.Size(347, 205);
            this.groupBoxChat.TabIndex = 8;
            this.groupBoxChat.TabStop = false;
            this.groupBoxChat.Text = "Chat";
            // 
            // listBox1
            // 
            this.listBox1.FormattingEnabled = true;
            this.listBox1.Location = new System.Drawing.Point(6, 21);
            this.listBox1.Name = "listBox1";
            this.listBox1.Size = new System.Drawing.Size(335, 121);
            this.listBox1.TabIndex = 2;
            // 
            // buttonDelMes
            // 
            this.buttonDelMes.Enabled = false;
            this.buttonDelMes.Location = new System.Drawing.Point(188, 167);
            this.buttonDelMes.Name = "buttonDelMes";
            this.buttonDelMes.Size = new System.Drawing.Size(131, 23);
            this.buttonDelMes.TabIndex = 1;
            this.buttonDelMes.Text = "Ștergere mesaj selectat";
            this.buttonDelMes.UseVisualStyleBackColor = true;
            this.buttonDelMes.Click += new System.EventHandler(this.button2_Click);
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(7, 167);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(150, 23);
            this.button1.TabIndex = 0;
            this.button1.Text = "Vizualizare mesaje";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // buttonAddQuestion
            // 
            this.buttonAddQuestion.Location = new System.Drawing.Point(116, 172);
            this.buttonAddQuestion.Name = "buttonAddQuestion";
            this.buttonAddQuestion.Size = new System.Drawing.Size(121, 23);
            this.buttonAddQuestion.TabIndex = 0;
            this.buttonAddQuestion.Text = "Adăugare întrebare";
            this.buttonAddQuestion.UseVisualStyleBackColor = true;
            this.buttonAddQuestion.Click += new System.EventHandler(this.buttonAddQuestion_Click);
            // 
            // textBoxQuest
            // 
            this.textBoxQuest.Location = new System.Drawing.Point(116, 55);
            this.textBoxQuest.Name = "textBoxQuest";
            this.textBoxQuest.ScrollBars = System.Windows.Forms.ScrollBars.Horizontal;
            this.textBoxQuest.Size = new System.Drawing.Size(121, 20);
            this.textBoxQuest.TabIndex = 2;
            // 
            // textBoxAnsCor
            // 
            this.textBoxAnsCor.Location = new System.Drawing.Point(116, 98);
            this.textBoxAnsCor.Name = "textBoxAnsCor";
            this.textBoxAnsCor.ScrollBars = System.Windows.Forms.ScrollBars.Horizontal;
            this.textBoxAnsCor.Size = new System.Drawing.Size(121, 20);
            this.textBoxAnsCor.TabIndex = 3;
            // 
            // textBoxScor
            // 
            this.textBoxScor.Location = new System.Drawing.Point(116, 132);
            this.textBoxScor.Name = "textBoxScor";
            this.textBoxScor.ScrollBars = System.Windows.Forms.ScrollBars.Horizontal;
            this.textBoxScor.Size = new System.Drawing.Size(121, 20);
            this.textBoxScor.TabIndex = 4;
            // 
            // textBoxAns1
            // 
            this.textBoxAns1.Location = new System.Drawing.Point(340, 22);
            this.textBoxAns1.Name = "textBoxAns1";
            this.textBoxAns1.Size = new System.Drawing.Size(121, 20);
            this.textBoxAns1.TabIndex = 5;
            // 
            // textBoxAns2
            // 
            this.textBoxAns2.Location = new System.Drawing.Point(340, 58);
            this.textBoxAns2.Name = "textBoxAns2";
            this.textBoxAns2.Size = new System.Drawing.Size(121, 20);
            this.textBoxAns2.TabIndex = 6;
            // 
            // textBoxAns3
            // 
            this.textBoxAns3.Location = new System.Drawing.Point(340, 93);
            this.textBoxAns3.Name = "textBoxAns3";
            this.textBoxAns3.ScrollBars = System.Windows.Forms.ScrollBars.Horizontal;
            this.textBoxAns3.Size = new System.Drawing.Size(121, 20);
            this.textBoxAns3.TabIndex = 7;
            // 
            // textBoxAns4
            // 
            this.textBoxAns4.Location = new System.Drawing.Point(340, 132);
            this.textBoxAns4.Name = "textBoxAns4";
            this.textBoxAns4.ScrollBars = System.Windows.Forms.ScrollBars.Horizontal;
            this.textBoxAns4.Size = new System.Drawing.Size(121, 20);
            this.textBoxAns4.TabIndex = 8;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(6, 135);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(84, 13);
            this.label3.TabIndex = 9;
            this.label3.Text = "Scorul întrebării:";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(6, 96);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(85, 13);
            this.label4.TabIndex = 10;
            this.label4.Text = "Răspuns corect:";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(271, 132);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(63, 13);
            this.label5.TabIndex = 11;
            this.label5.Text = "Răspuns D:";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(272, 58);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(62, 13);
            this.label6.TabIndex = 12;
            this.label6.Text = "Răspuns B:";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(272, 25);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(62, 13);
            this.label7.TabIndex = 13;
            this.label7.Text = "Răspuns A:";
            // 
            // labelQuest
            // 
            this.labelQuest.AutoSize = true;
            this.labelQuest.Location = new System.Drawing.Point(6, 58);
            this.labelQuest.Name = "labelQuest";
            this.labelQuest.Size = new System.Drawing.Size(95, 13);
            this.labelQuest.TabIndex = 14;
            this.labelQuest.Text = "Conținut întrebare:";
            // 
            // labelEpoca
            // 
            this.labelEpoca.AutoSize = true;
            this.labelEpoca.Location = new System.Drawing.Point(6, 25);
            this.labelEpoca.Name = "labelEpoca";
            this.labelEpoca.Size = new System.Drawing.Size(77, 13);
            this.labelEpoca.TabIndex = 15;
            this.labelEpoca.Text = "Numele epocii:";
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Location = new System.Drawing.Point(272, 96);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(62, 13);
            this.label10.TabIndex = 16;
            this.label10.Text = "Răspuns C:";
            // 
            // comboBoxEpoca
            // 
            this.comboBoxEpoca.FormattingEnabled = true;
            this.comboBoxEpoca.Items.AddRange(new object[] {
            "Preistorica",
            "Antica",
            "Medievala",
            "Moderna",
            "Contemporana"});
            this.comboBoxEpoca.Location = new System.Drawing.Point(116, 19);
            this.comboBoxEpoca.Name = "comboBoxEpoca";
            this.comboBoxEpoca.Size = new System.Drawing.Size(121, 21);
            this.comboBoxEpoca.TabIndex = 17;
            // 
            // buttonRemoveContent
            // 
            this.buttonRemoveContent.Location = new System.Drawing.Point(340, 172);
            this.buttonRemoveContent.Name = "buttonRemoveContent";
            this.buttonRemoveContent.Size = new System.Drawing.Size(121, 23);
            this.buttonRemoveContent.TabIndex = 18;
            this.buttonRemoveContent.Text = "Eliminare conținut";
            this.buttonRemoveContent.UseVisualStyleBackColor = true;
            this.buttonRemoveContent.Click += new System.EventHandler(this.buttonRemoveContent_Click);
            // 
            // BrainyBattleAdmin
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(805, 449);
            this.Controls.Add(this.groupBoxChat);
            this.Controls.Add(this.groupBoxQuiz);
            this.Controls.Add(this.groupBoxUser);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.textBoxParola);
            this.Controls.Add(this.textBoxUsername);
            this.Controls.Add(this.buttonConectare);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.Name = "BrainyBattleAdmin";
            this.Text = "BrainyBattleAdmin";
            this.groupBoxUser.ResumeLayout(false);
            this.groupBoxQuiz.ResumeLayout(false);
            this.groupBoxQuiz.PerformLayout();
            this.groupBoxChat.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button buttonConectare;
        private System.Windows.Forms.TextBox textBoxUsername;
        private System.Windows.Forms.TextBox textBoxParola;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.GroupBox groupBoxUser;
        private System.Windows.Forms.GroupBox groupBoxQuiz;
        private System.Windows.Forms.GroupBox groupBoxChat;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Button buttonDelMes;
        private System.Windows.Forms.ListBox listBox1;
        private System.Windows.Forms.ListBox listBox2;
        private System.Windows.Forms.Button buttonViewUser;
        private System.Windows.Forms.Button buttonDeleteUser;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.Label labelEpoca;
        private System.Windows.Forms.Label labelQuest;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox textBoxAns4;
        private System.Windows.Forms.TextBox textBoxAns3;
        private System.Windows.Forms.TextBox textBoxAns2;
        private System.Windows.Forms.TextBox textBoxAns1;
        private System.Windows.Forms.TextBox textBoxScor;
        private System.Windows.Forms.TextBox textBoxAnsCor;
        private System.Windows.Forms.TextBox textBoxQuest;
        private System.Windows.Forms.Button buttonAddQuestion;
        private System.Windows.Forms.ComboBox comboBoxEpoca;
        private System.Windows.Forms.Button buttonRemoveContent;
    }
}

