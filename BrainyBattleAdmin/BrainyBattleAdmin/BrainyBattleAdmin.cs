using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Net.Http;
namespace BrainyBattleAdmin
{
    public partial class BrainyBattleAdmin : Form
    {
        private string _token;
        public BrainyBattleAdmin()
        {
            InitializeComponent();
            _token = "";
        }

        private async void buttonConectare_ClickAsync(object sender, EventArgs e)
        {
            if (_token == "")
            {
                var client = new HttpClient();
                string username = textBoxUsername.Text;
                string parola = textBoxParola.Text;
                if (username == "" || parola == "")
                    MessageBox.Show("Datele trebuie sa fie completate!");
                if (username != "admin")
                    MessageBox.Show("Doar adminul are voie sa foloaseaca aceasta aplicatie!");
                var jsonContent = new StringContent(
                    "{" +
                    $"\"username\":\"{textBoxUsername.Text}\"," +
                    $"\"password\":\"{textBoxParola.Text}\"" +
                    "}",
                    Encoding.UTF8,
                    "application/json"
                );
                HttpResponseMessage response = await client.PostAsync("http://localhost:8080/uac/login", jsonContent);
                if (response.StatusCode == System.Net.HttpStatusCode.Forbidden)
                {
                    MessageBox.Show("Credențiale incorecte!");
                }
                else if (response.IsSuccessStatusCode)
                {
                    string responseContent = await response.Content.ReadAsStringAsync();
                    _token = responseContent.Substring(10, responseContent.Length - 12);
                    groupBoxChat.Enabled = true;
                    groupBoxUser.Enabled = true;
                    groupBoxQuiz.Enabled = true;
                    buttonConectare.Text = "Deconectare";
                }
            }
            else
            {
                var client = new HttpClient();
                string token = "Bearer " + _token;
                client.DefaultRequestHeaders.Add("Authorization", token);
                HttpResponseMessage response = await client.PostAsync("http://localhost:8080/uac/logout",null);
                if (response.StatusCode == System.Net.HttpStatusCode.OK)
                {
                   // MessageBox.Show("Deconectare reușită!");
                    buttonConectare.Text = "Conectare";
                    groupBoxUser.Enabled = false;
                    groupBoxQuiz.Enabled = false;
                    groupBoxChat.Enabled = false;
                    listBox1.DataSource = null;
                    listBox2.DataSource = null;
                    buttonDelMes.Enabled = false;
                    buttonDeleteUser.Enabled = false;
                    _token = "";
                }
                else
                {
                    MessageBox.Show("A eșuat deconectarea!");
                }
            }
        }

        private async void button1_Click(object sender, EventArgs e)
        {
            var client = new HttpClient();
            string token = "Bearer "+_token;
            client.DefaultRequestHeaders.Add("Authorization", token);
            HttpResponseMessage response = await client.GetAsync("http://localhost:8080/chat/admin");
            string responseContent = await response.Content.ReadAsStringAsync();
            char[] trimchars = {'[',']'};
            string[] messages = responseContent.Trim(trimchars).Replace(',',' ').Split('}').Where(x=>!string.IsNullOrEmpty(x)).ToArray();
            listBox1.DataSource = messages;
            listBox1.HorizontalScrollbar = true;

            if (listBox1.Items.Count>0)
            {
                buttonDelMes.Enabled = true;
            }
        }

        private async void button2_Click(object sender, EventArgs e)
        {
            var client = new HttpClient();
            string token = "Bearer " + _token;
            client.DefaultRequestHeaders.Add("Authorization", token);
            int selectedIndex = listBox1.SelectedIndex;

            string delItem = listBox1.Items[listBox1.SelectedIndex].ToString();
            if (delItem.IndexOf("\"id\":") < 0)
            {
                MessageBox.Show("Mesaj invalid!");
                return;
            }
            delItem = delItem.Substring(delItem.IndexOf("\"id\":"));
            delItem = delItem.Substring(0, delItem.IndexOf(' '));
            delItem = delItem.Substring(5);
            HttpResponseMessage response = await client.DeleteAsync("http://localhost:8080/chat/"+delItem);
            if (response.StatusCode == System.Net.HttpStatusCode.NoContent)
                MessageBox.Show("Mesaj sters cu succes!");
            else
            {
                MessageBox.Show("Mesajul nu a putut fi sters!");
                return;
            }
            List<object> currentList = new List<object>();
            foreach (object item in listBox1.Items)
            {
                currentList.Add(item);
            }
            currentList.RemoveAt(selectedIndex);
            listBox1.DataSource = currentList;
            if (listBox1.Items.Count == 0)
            {
                buttonDelMes.Enabled = false;
            }
            
        }

        private async void button3_Click(object sender, EventArgs e)
        {
            var client = new HttpClient();
            string token = "Bearer " + _token;
            client.DefaultRequestHeaders.Add("Authorization", token);
            HttpResponseMessage response = await client.GetAsync("http://localhost:8080/uac/accounts");
            string responseContent = await response.Content.ReadAsStringAsync();
            char[] trimchars = { '[', ']' };
            string[] users = responseContent.Trim(trimchars).Replace(',', ' ').Split('}').Where(x => !string.IsNullOrEmpty(x)).ToArray();
            listBox2.DataSource = users;
            listBox2.HorizontalScrollbar = true;
            if (listBox2.Items.Count > 1)
            {
                buttonDeleteUser.Enabled = true;
            }
        }

        private async void buttonDeleteUser_Click(object sender, EventArgs e)
        {
            int selectedIndex = listBox2.SelectedIndex;
            if (selectedIndex == 0)//Adminul ar trebui sa fie primul utilizator
            {
                MessageBox.Show("Adminul nu se poate sterge pe el insusi!");
                return;
            }
            string delUser = listBox2.Items[selectedIndex].ToString();
            delUser = delUser.Substring(delUser.IndexOf("\"id\":"));
            delUser = delUser.Substring(5);
            var client = new HttpClient();
            string token = "Bearer " + _token;
            client.DefaultRequestHeaders.Add("Authorization", token);
            HttpResponseMessage response = await client.DeleteAsync("http://localhost:8080/uac/accounts/"+delUser);
            if (response.StatusCode == System.Net.HttpStatusCode.NoContent)
                MessageBox.Show("User sters cu succes!");
            else
            {
                MessageBox.Show("Utilizatorul nu a putut fi sters!");
                return;
            }
            List<object> currentList = new List<object>();
            foreach (object item in listBox2.Items)
            {
                currentList.Add(item);
            }
            currentList.RemoveAt(selectedIndex);
            listBox2.DataSource = currentList;
            if (listBox2.Items.Count == 1)
            {
                buttonDeleteUser.Enabled = false;
            }
        }

        private async void buttonAddQuestion_Click(object sender, EventArgs e)
        {
            bool scoreValid = int.TryParse(textBoxScor.Text, out int score);
            if (!scoreValid)
            {
                MessageBox.Show("A fost introdus un scor invalid!");
                return;
            }
            if(comboBoxEpoca.SelectedIndex==-1 || textBoxQuest.Text=="" || textBoxAns2.Text=="" || textBoxAns1.Text=="" || textBoxAns4.Text=="" || textBoxAns3.Text=="" || textBoxAnsCor.Text=="" || textBoxScor.Text == "")
            {
                MessageBox.Show("Trebuie completate toate câmpurile!");
                return;
            }
            List<string> answers = new List<string> {textBoxAns1.Text, textBoxAns2.Text, textBoxAns3.Text, textBoxAns4.Text };
            if (!answers.Contains(textBoxAnsCor.Text))
            {
                MessageBox.Show("Răspunsul corect trebuie să fie printre cele introduse la întrebare!");
                return;
            }
            if (answers.Count != answers.Distinct().Count())
            {
                MessageBox.Show("Răspunsurile trebuie să fie unice!");
                return;
            }
            var client = new HttpClient();
            string token = "Bearer " + _token;
            client.DefaultRequestHeaders.Add("Authorization", token);
            var jsonContent = new StringContent(
                   "{" +
                   $"\"epoch_name\":\"{comboBoxEpoca.SelectedItem}\"," +
                   $"\"question\":\"{textBoxQuest.Text}\"," +
                   $"\"answers1\":\"{textBoxAns1.Text}\"," +
                   $"\"answers2\":\"{textBoxAns2.Text}\"," +
                   $"\"answers3\":\"{textBoxAns3.Text}\"," +
                   $"\"answers4\":\"{textBoxAns4.Text}\"," +
                   $"\"isCorrect\":\"{textBoxAnsCor.Text}\"," +
                   $"\"score\":{textBoxScor.Text}" +
                   "}",
                   Encoding.UTF8,
                   "application/json"
               );
            HttpResponseMessage response = await client.PostAsync("http://localhost:8080/quiz",jsonContent);
            if (response.StatusCode == System.Net.HttpStatusCode.Created)
            {
                MessageBox.Show("Întrebare creată cu succes!");
                return;
            }
            MessageBox.Show("Nu s-a creat întrebarea!");
        }

        private void buttonRemoveContent_Click(object sender, EventArgs e)
        {
            textBoxAns4.Text = "";
            textBoxAns3.Text = "";
            textBoxAnsCor.Text = "";
            textBoxScor.Text = "";
            textBoxAns1.Text = "";
            textBoxQuest.Text = "";
            textBoxAns2.Text = "";
            comboBoxEpoca.SelectedIndex = -1;
        }
    }
}
